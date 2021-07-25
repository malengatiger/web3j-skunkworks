package com.boha.myweb3.mongo;

import com.boha.myweb3.models.WalletFile;
import com.boha.myweb3.util.E;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MongoService implements DatabaseServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(MongoService.class.getSimpleName());

    public MongoService() {
        logger.info(E.PANDA +E.PANDA +E.PANDA +E.PANDA + "MongoService constructor");
    }

    @Autowired
    WalletFileRepository walletFileRepository;

    @Override
    public List<WalletFile> getWalletFiles() throws Exception {
        Iterable<WalletFile> mIt = walletFileRepository.findAll();
        List<WalletFile> list = new ArrayList<>();
        Iterator<WalletFile> bIt = mIt.iterator();
        while (bIt.hasNext()) {
            WalletFile w = bIt.next();
            list.add(w);
        }
        logger.info(E.PANDA +E.PANDA +E.PANDA +E.PANDA + "wallet files found: " + list.size());
        return list;
    }

    @Override
    public void addWalletFile(WalletFile walletFile) throws Exception {
        walletFileRepository.save(walletFile);
        logger.info(E.PANDA +E.PANDA +E.PANDA +E.PANDA + "wallet file added to mongodb:, \uD83C\uDF4E \uD83C\uDF4E yay! \uD83C\uDF4E ");
    }
}
