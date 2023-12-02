package com.example.HTTD.Service.IMPL;

import com.example.HTTD.Entity.Wallet;
import com.example.HTTD.Repository.WalletRepository;
import com.example.HTTD.Service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;
    @Override
    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getWalletById(Long walletId) {
        Optional<Wallet> optionalWallet = walletRepository.findById(walletId);
        if (optionalWallet.isPresent()) {
            return optionalWallet.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Wallet> getAllWallet() {
        return walletRepository.findAll()   ;
    }

    @Override
    public Wallet updateWallet(Wallet wallet) {
        Wallet existingwallet = walletRepository.findById(wallet.getId()).get();
        existingwallet.setName(wallet.getName());
        existingwallet.setAmount(wallet.getAmount());
        existingwallet.setDate_created(wallet.getDate_created());
        Wallet updatedWallet = walletRepository.save(existingwallet);
        return updatedWallet;
    }

    @Override
    public boolean deleteWallet(Long walletId) {
        Optional<Wallet> walletOptional = walletRepository.findById(walletId);
        if (walletOptional.isPresent()) {
            walletRepository.deleteById(walletId);
            return true;
        }
        return false;
    }
}
