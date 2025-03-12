package org.example.StrategyPattern;

class StudentSearchContext {
    private StudentSearchStrategy strategy;

    public void setSearchStrategy(StudentSearchStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeSearch(String queryParam, String url, String user, String password) {
        if (strategy == null) {
            System.out.println("No search strategy selected.");
            return;
        }
        strategy.search(queryParam, url, user, password);
    }
}