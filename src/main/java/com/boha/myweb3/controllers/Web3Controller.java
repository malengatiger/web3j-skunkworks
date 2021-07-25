package com.boha.myweb3.controllers;


import com.boha.myweb3.models.WalletFile;
import com.boha.myweb3.services.Web3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.boha.myweb3.util.E;

@RestController
public class Web3Controller {
    private static final Logger logger = LoggerFactory.getLogger(Web3Controller.class.getSimpleName());
    @Autowired
    Web3Service web3Service;

    @GetMapping("/start")
    public ResponseEntity<Object> start() {
        logger.info(E.ANGRY +E.ANGRY +E.ANGRY + "Web3Controller : /start requested.... " + E.BLUE_DOT + E.BLUE_DOT);
        try {
            String msg = web3Service.createWeb3j();
            return ResponseEntity.accepted().body(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("We done fucked up! " + e.getMessage());
        }
    }

    @GetMapping("/createWallet")
    public ResponseEntity<Object> createWallet(@RequestParam String password) {
        logger.info(E.ANGRY +E.ANGRY +E.ANGRY + "Web3Controller : /createWallet requested.... " + E.BLUE_DOT + E.BLUE_DOT);
        try {
            WalletFile walletFile = web3Service.createWallet(password);
            return ResponseEntity.ok().body(walletFile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("We done fucked up! " + e.getMessage());
        }
    }
}
