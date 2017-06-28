package br.com.bjbraz;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.utils.Convert;

/**
 * Using web3j in Parity
 * 
 * @author alexjavabraz
 *
 */
public class UsingOnParity {
	
	public static final String WALLET_PASSWORD = "suzuki";
	
	/*
    If you want to use regular Ethereum wallet addresses, provide a WALLET address variable
    "0x..." // 20 bytes (40 hex characters) & replace instances of ALICE.getAddress() with this
    WALLET address variable you've defined.
    */

	static final String WALLET_ORIGEN = "0x009761303A662654c87e3F9eca3Fe34cB851f662";

	static final String WALLET_DESTINO = "0x293012E3F5CE9562B1Fdd440c76c766cFfF3e1D4";

	private static final BigInteger ACCOUNT_UNLOCK_DURATION = BigInteger.valueOf(30);
    private static final int SLEEP_DURATION = 15000;
    private static final int ATTEMPTS = 40;
    static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    static final BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000);
    
    private Web3j web3    = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
	private Parity parity = Parity.build(new HttpService()); // defaults to http://localhost:8545/

	/**
     * 
     * @param args
     */
	public static void main(String[] args) {
		UsingOnParity o = new UsingOnParity();
		o.start();
//		o.sendTest();
		o.callRegisterContract();
	}
	
	private void callRegisterContract(){
		
		try{
			
			File json = new File(getClass().getClassLoader().getResource("accounts.json").getFile());
//			Credentials credentials = WalletUtils.loadCredentials(WALLET_PASSWORD, json);
			
//			Register registro = Register.load("0x19076364aD2FAAef6E168573083CC7121B599C02", 
//					web3, credentials, GAS_PRICE, GAS_LIMIT);
			
			TransactionManager transactionManager = new ClientTransactionManager(web3, WALLET_ORIGEN);
			
			Register registro = Register.load("0x19076364aD2FAAef6E168573083CC7121B599C02", web3, transactionManager, GAS_PRICE, GAS_LIMIT);
			TransactionReceipt receipt = registro.itenQuantity().get();
			System.out.println(receipt);
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void sendTest() {

		try{
			unlockAccount();
			
	        BigInteger nonce = getNonce(WALLET_ORIGEN);
	        BigInteger value = Convert.toWei("0.01", Convert.Unit.ETHER).toBigInteger();
	
	        Transaction transaction = Transaction.createEtherTransaction(
	        		WALLET_ORIGEN, nonce, GAS_PRICE, GAS_LIMIT, WALLET_DESTINO, value);
	
	        EthSendTransaction ethSendTransaction =
	                parity.ethSendTransaction(transaction).sendAsync().get();
	
	        String transactionHash = ethSendTransaction.getTransactionHash();
	
	        TransactionReceipt transactionReceipt = waitForTransactionReceipt(transactionHash);
	        System.out.println("Sucesso ! ");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param transactionHash
	 * @return
	 * @throws Exception
	 */
	private final TransactionReceipt waitForTransactionReceipt(
            String transactionHash) throws Exception {

        Optional<TransactionReceipt> transactionReceiptOptional =
                getTransactionReceipt(transactionHash, SLEEP_DURATION, ATTEMPTS);

        if (!transactionReceiptOptional.isPresent()) {
            System.out.println("Transaction receipt not generated after " + ATTEMPTS + " attempts");
        }

        return transactionReceiptOptional.get();
    }
	
	/**
	 * 
	 * @param transactionHash
	 * @param sleepDuration
	 * @param attempts
	 * @return
	 * @throws Exception
	 */
	private Optional<TransactionReceipt> getTransactionReceipt(
            String transactionHash, int sleepDuration, int attempts) throws Exception {

        Optional<TransactionReceipt> receiptOptional =
                sendTransactionReceiptRequest(transactionHash);
        for (int i = 0; i < attempts; i++) {
            if (!receiptOptional.isPresent()) {
                Thread.sleep(sleepDuration);
                receiptOptional = sendTransactionReceiptRequest(transactionHash);
            } else {
                break;
            }
        }

        return receiptOptional;
    }
	
	/**
	 * 
	 * @param transactionHash
	 * @return
	 * @throws Exception
	 */
	private Optional<TransactionReceipt> sendTransactionReceiptRequest(
            String transactionHash) throws Exception {
        EthGetTransactionReceipt transactionReceipt =
                parity.ethGetTransactionReceipt(transactionHash).sendAsync().get();

        return transactionReceipt.getTransactionReceipt();
    }
	
	/**
	 * 
	 * @param address
	 * @return
	 * @throws Exception
	 */
	private BigInteger getNonce(String address) throws Exception {
		Parity parity = Parity.build(new HttpService()); // defaults to
        EthGetTransactionCount ethGetTransactionCount = parity.ethGetTransactionCount(
                address, DefaultBlockParameterName.LATEST).sendAsync().get();
        return ethGetTransactionCount.getTransactionCount();
    }
	
//	EthAccounts accounts = parity.ethAccounts().send();
//	List<String> contas  = accounts.getAccounts();
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean unlockAccount() throws Exception {
        PersonalUnlockAccount personalUnlockAccount =
                parity.personalUnlockAccount(
                		WALLET_ORIGEN, WALLET_PASSWORD)
                        .sendAsync().get();
        return personalUnlockAccount.accountUnlocked();
    }

	/**
	 * 
	 */
	private void start() {
		try {
			Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			System.out.println("Vers�o do Client : " + clientVersion);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	 * @param filePath
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	static String load(String filePath) throws URISyntaxException, IOException {
        URL url = UsingOnParity.class.getClass().getResource(filePath);
        byte[] bytes = Files.readAllBytes(Paths.get(url.toURI()));
        return new String(bytes);
    }

}
