package com.boha.myweb3.mongo;


import com.boha.myweb3.models.WalletFile;

import java.util.List;

public interface DatabaseServiceInterface {

    List<WalletFile> getWalletFiles() throws Exception;

    void addWalletFile(WalletFile walletFile) throws Exception;

}
