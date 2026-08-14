package com.sccs.cli;

public class commandFactory {
    private commandFactory() {
        System.out.print("default factory created");
    } //defaukt constructor

    private static commandFactory _instance;

    private static commandFactory getInstance() { //singleton
        if(_instance == null) {
            _instance = new commandFactory();
        }
        return _instance;
    }

    public sccsCommand createCommand(commandType type) {
        switch (type) {
            case ADMIN:
                return new adminCommand();
            case GET:
                return new getCommand();
            case DELTA:
                return new deltaCommand();
            case PRS:
                return new prsCommand();
        }
        return null; // for now.
    }
}
