package com.flightplan;

public enum StrategyFactoryEnum {
    SHORTEST {
        @Override
        public Strategy getInstance() {
            return new ShortestStrategy();
        }
    };

    public abstract Strategy getInstance();
}
