package com.example.HTTD.Service;

import com.example.HTTD.Entity.Wallet;

import java.util.List;

public interface WalletService {
    Wallet createWallet(Wallet wallet);

    Wallet getWalletById(Long walletId);

    List<Wallet> getAllWallet();

    Wallet updateWallet(Wallet wallet);

    boolean deleteWallet(Long walletId);
}
