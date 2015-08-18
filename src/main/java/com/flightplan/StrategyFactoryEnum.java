package com.flightplan;

public enum StrategyFactoryEnum {
    SHORTEST_BFS {
        @Override
        public Strategy getInstance() {
            return new ShortestBFSStrategy();
        }
    },SHORTEST_DFS {
        @Override
        public Strategy getInstance() {
            return new ShortestDFSStrategy();
        }
    };

    public abstract Strategy getInstance();
}
