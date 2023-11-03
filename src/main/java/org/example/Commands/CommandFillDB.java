package org.example.Commands;

import org.example.Service.JDBCStorageService;
import org.example.Service.StorageService;

public class CommandFillDB implements Command {
    private final StorageService storageService;
    public CommandFillDB(StorageService storageService){
        this.storageService = storageService;
    }
    @Override
    public void execute(String[] parameters){
        storageService.fillDB();
    }
}
