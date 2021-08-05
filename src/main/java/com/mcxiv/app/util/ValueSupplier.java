package com.mcxiv.app.util;

public interface ValueSupplier {
    float get();
    default int getValue(int Expr){
        return (int) (Expr * get() / 100f);
    }
}
