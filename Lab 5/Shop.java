class Shop {

    private final ImList<Server> servers;

    Shop(ImList<Server> servers) {
        this.servers = servers;
    }

    public ImList<Server> getServers() {
        return servers;
    }

    public boolean availableServer(double eventTime) {
        for (int i = 0; i < getServers().size(); i++) {
            Server s = getServers().get(i);
            if (s.getIsIdle(eventTime)) {
                return true;
            }
        }
        return false;
    }

    public Server findServer(double eventTime) {
        for (int i = 0; i < getServers().size(); i++) {
            Server s = getServers().get(i);
            if (s.getIsIdle(eventTime)) {
                return s;
            }
        }
        return new Server(0);
    }

    public boolean canQueue() {
        for (int i = 0; i < getServers().size(); i++) {
            Server s = getServers().get(i);
            if (s.canQueue()) {
                return true;
            }
        }
        return false;
    }

    public Server findQueue() {
        for (int i = 0; i < getServers().size(); i++) {
            Server s = getServers().get(i);
            if (s.canQueue()) {
                return s;
            }
        }
        return new Server(0);
    }        

    public Shop updateServer(Server s) {
        ImList<Server> newServers = servers.set(s.getServerNum() - 1, s);
        Shop newShop = new Shop(newServers);
        return newShop;
    }
}

