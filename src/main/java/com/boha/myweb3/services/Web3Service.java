package com.boha.myweb3.services;

import com.boha.myweb3.models.WalletFile;
import com.boha.myweb3.mongo.MongoService;
import com.boha.myweb3.util.E;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/*
web3j solidity generate --solidityTypes contract.bin contract.abi .bin .abi -o src/main/java -p com.boha
 */

@Service
public class Web3Service {
    private static final Logger logger = LoggerFactory.getLogger(Web3Service.class.getSimpleName());
    private Web3j web3j;
    private static final Gson G = new GsonBuilder().setPrettyPrinting().create();
//    private Credentials credentials;

    @Value("${nodeEndpoint}")
    private String nodeEndpoint;

    @Value("${fromAddress}")
    private String fromAddress;

    @Autowired
    MongoService mongoService;

    public Web3Service() {
        logger.info(E.ALIEN +E.ALIEN +E.ALIEN +E.ALIEN +
                "......... Web3Service constructor: .... will create web3j ... "
                + E.BROCCOLI + E.BROCCOLI + E.BROCCOLI + E.BROCCOLI);
        String msg = createWeb3j();
        logger.info(E.ALIEN +E.ALIEN +E.ALIEN +E.ALIEN + msg);
    }

    public String createWeb3j() {
        logger.info(E.ALIEN +E.ALIEN +E.ALIEN +E.ALIEN + "......... " +
                "Web3Service : start .... " + E.BROCCOLI + E.BROCCOLI + E.BROCCOLI + E.BROCCOLI);
        try {
            logger.info(E.ALIEN +E.ALIEN +E.ALIEN +E.ALIEN + "Web3Service : " +
                    "building Web3j with HTTPService .... " + E.BROCCOLI + E.BROCCOLI);
            if (web3j == null) {
                web3j = Web3j.build(new HttpService(nodeEndpoint));
                logger.info(E.ALIEN + E.ALIEN + E.ALIEN + E.ALIEN + "Web3Service :  \uD83C\uDF4E  \uD83C\uDF4E  \uD83C\uDF4E " +
                        "Web3j built OK .... " + web3j.ethChainId().getId() + " " + E.BLUE_BIRD + E.BLUE_BIRD);
            }



            return "We good with this: \uD83C\uDF4E  \uD83C\uDF4E  \uD83C\uDF4E " +
                    "ethBlockNumber: " + web3j.ethBlockNumber().getId() + " \uD83D\uDD06 ethChainId: "
                    + web3j.ethChainId().getId() + " \uD83D\uDD06  gasPrice Id: "
                    + web3j.ethGasPrice().getId() + " \uD83D\uDD06 params: "
                    + web3j.adminNodeInfo().getParams() + " " + E.LEAF + E.LEAF + E.LEAF;
        } catch (Exception e) {
            e.printStackTrace();
            return " \uD83D\uDE21 \uD83D\uDE21 \uD83D\uDE21 We fucked, Boss!";
        }
    }
    public WalletFile createWallet(String password) throws Exception {
//        File mFile = new File("creds_" + System.currentTimeMillis());
//
//        Credentials credentials = WalletUtils.loadCredentials(password, mFile.getAbsolutePath());
//        Credentials credentials = WalletUtils.loadCredentials(
//                "kktiger3",
//                "/Users/aubs/Library/Ethereum/testnet/keystore/UTC--2021-07-24T14-26-19.929270000Z--fd661af1016caf5ebe91dc107b7694df8fa7fb2a.json");

//        logger.info(E.ALIEN +E.ALIEN +E.ALIEN +E.ALIEN + "Web3Service :  \uD83C\uDF4E  \uD83C\uDF4E  \uD83C\uDF4E " +
//                "WALLET: \uD83D\uDD06 \uD83D\uDD06 " +
//                "credentials.address: " + credentials.getAddress() + " \uD83D\uDD06 \uD83D\uDD06 ");
        logger.info(E.ALIEN + E.ALIEN + E.ALIEN + E.ALIEN + " ......... creating a Wallet ........ ");

        try {
            File walletDirectory = new File("wallet_directory");
            if (!walletDirectory.exists()) {
                walletDirectory.mkdir();
            }
            String filePath = WalletUtils.generateNewWalletFile(password, walletDirectory, true); //The method returns the walletDirectory name of the created wallet
            logger.info(E.ALIEN + E.ALIEN + E.ALIEN + E.ALIEN + "createWallet : filePath: " + filePath + " " + E.RED_APPLE + E.RED_APPLE);
            String path = walletDirectory.getAbsolutePath() + "/" + filePath;
            logger.info(E.ALIEN + E.ALIEN + E.ALIEN + E.ALIEN + "createWallet : complete path: " + path + " " + E.RED_APPLE + E.RED_APPLE);
            Path walletFile = Path.of(path);
            String contents = Files.readString(walletFile);
            logger.info(E.ALIEN + E.ALIEN + E.ALIEN + E.ALIEN + " wallet walletDirectory contents: \n" + contents + " \n\n");
            WalletFile wFile = G.fromJson(contents, WalletFile.class);
            logger.info(E.ALIEN + E.ALIEN + E.ALIEN + E.ALIEN + " WalletFile object to be sent back: \uD83C\uDF4E " + G.toJson(wFile));
            mongoService.addWalletFile(wFile);

            List<WalletFile> savedWallets = mongoService.getWalletFiles();
            logger.info(E.ALIEN + E.ALIEN + E.ALIEN + E.ALIEN + " savedWallets on database: \uD83D\uDD35 \uD83D\uDD35  "
                    + savedWallets.size() + " walletFiles \uD83D\uDD35 \uD83D\uDD35  ");

            return wFile;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Wallet Create Failed: " + e.getMessage());
        }

    }

    public void getBalance() throws Exception {
        String file = WalletUtils.generateLightNewWalletFile("piot123", null);
        Credentials credentials = WalletUtils.loadCredentials("piot123", file);
        logger.info("Credentials created: file={}, address={}", file, credentials.getAddress());
        EthCoinbase coinbase = web3j.ethCoinbase().send();
        EthGetTransactionCount transactionCount = web3j.ethGetTransactionCount(coinbase.getAddress(), DefaultBlockParameterName.LATEST).send();
        Transaction transaction = Transaction.createEtherTransaction(coinbase.getAddress(), transactionCount.getTransactionCount(), BigInteger.valueOf(20_000_000_000L), BigInteger.valueOf(21_000), credentials.getAddress(),BigInteger.valueOf(25_000_000_000_000_000L));
        web3j.ethSendTransaction(transaction).send();
        EthGetBalance balance = web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        logger.info("Balance: {}", balance.getBalance().longValue());
    }

    public void getTransactionReceipt() throws Exception {
        TransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt("add transaction hash to get receipt")
                .send().getTransactionReceipt().get();
    }
    public void getTransactionByHash(String transactionHash) throws Exception {
        Request<?, EthTransaction> transaction = web3j.ethGetTransactionByHash(transactionHash);
    }

    public void loadContract(File file) throws Exception {

        //Web3j web3j = Web3j.build(new HttpService("<your_node_url>"));
        Credentials credentials = WalletUtils.loadCredentials("piot123", file);
        String greeting;
        contracts.HelloWorld helloWorld = contracts.HelloWorld.load(fromAddress, web3j, credentials, new DefaultGasProvider());
        if (helloWorld.isValid()) {
            greeting = helloWorld.greeting().send();
        }
        //web3j.shutdown();
    }
}
