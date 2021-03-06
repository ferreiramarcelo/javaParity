package br.com.bjbraz;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.2.1.
 */
public final class Register extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b60405160208061059f83398101604052515b60008054600160a060020a03191633600160a060020a031617905560018190555b505b61054f806100506000396000f300606060405263ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166382fbdc9c811461006657806383197ef0146100cd5780638da5cb5b146100df5780639a64a27614610118578063e5d1bdc2146101a3575bfe5b6100b4600480803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437509496506101bd95505050505050565b6040805192835260208301919091528051918290030190f35b34156100d557fe5b6100dd610242565b005b34156100e757fe5b6100ef610284565b6040805173ffffffffffffffffffffffffffffffffffffffff9092168252519081900360200190f35b6101236004356102a0565b604080516020808252835181830152835191928392908301918501908083838215610169575b80518252602083111561016957601f199092019160209182019101610149565b505050905090810190601f1680156101955780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6101ab6103b2565b60408051918252519081900360200190f35b600280544291600091600181016101d483826103b8565b916000526020600020906004020160005b50604080516080810182528681526020808201869052918101869052436060820152865190929161021a9183918901906103ea565b506020820151816001015560408201518160020155606082015181600301555050505b915091565b6000543373ffffffffffffffffffffffffffffffffffffffff908116911614156102815760005473ffffffffffffffffffffffffffffffffffffffff16ff5b5b565b60005473ffffffffffffffffffffffffffffffffffffffff1681565b6102a8610469565b60005b60025481101561039a57826002828154811015156102c557fe5b906000526020600020906004020160005b506001015414156103915760028054829081106102ef57fe5b906000526020600020906004020160005b50805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156103855780601f1061035a57610100808354040283529160200191610385565b820191906000526020600020905b81548152906001019060200180831161036857829003601f168201915b505050505091506103ac565b5b6001016102ab565b60408051602081019091526000815291505b50919050565b60005b90565b8154818355818115116103e4576004028160040283600052602060002091820191016103e4919061047b565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061042b57805160ff1916838001178555610458565b82800160010185558215610458579182015b8281111561045857825182559160200191906001019061043d565b5b506104659291506104ba565b5090565b60408051602081019091526000815290565b6103b591905b8082111561046557600061049582826104db565b50600060018201819055600282018190556003820155600401610481565b5090565b90565b6103b591905b8082111561046557600081556001016104c0565b5090565b90565b50805460018160011615610100020316600290046000825580601f10610501575061051f565b601f01602090049060005260206000209081019061051f91906104ba565b5b505600a165627a7a72305820e6676f0d76ba84d852233ff19b761bc6c14ff9209e1a90b0f778c23f7b3378e70029";

    private Register(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Register(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> register(DynamicBytes _data) {
        Function function = new Function("register", Arrays.<Type>asList(_data), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> destroy() {
        Function function = new Function("destroy", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> findById(Uint256 x) {
        Function function = new Function("findById", Arrays.<Type>asList(x), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> itenQuantity() {
        Function function = new Function("itenQuantity", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<Register> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Bytes32 _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_name));
        return deployAsync(Register.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Future<Register> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Bytes32 _name) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_name));
        return deployAsync(Register.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Register load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Register(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Register load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Register(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
