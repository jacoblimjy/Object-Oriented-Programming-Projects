ImList<Service> serveCruises(ImList<Cruise> cruises) {
    ImList<Service> activeServices = new ImList<Service>();
    ImList<Service> expiredServices = new ImList<Service>();
    ImList<Service> logbook = new ImList<Service>();
    int nextLoaderID = 0;

    for (Cruise c : cruises) {
        for (int i = 0; i < activeServices.size(); i++) {
            Service s = activeServices.get(i);
            if (s.getServiceEndTime() <= c.getArrivalTime()) {
                activeServices = activeServices.remove(i);
                expiredServices = expiredServices.add(s);
                i--;
            }
        }
        int numOfLoadersRequired = c.getNumOfLoadersRequired();
        while (numOfLoadersRequired > 0 && !expiredServices.isEmpty()) {
            Service service = expiredServices.get(0);
            expiredServices = expiredServices.remove(0);
            Loader loader = service.getLoader();
            service = new Service(loader, c);
            activeServices = activeServices.add(service);
            logbook = logbook.add(service);
            numOfLoadersRequired--;
        }
        while (numOfLoadersRequired > 0 ) {
            nextLoaderID += 1;
            Service service = new Service(new Loader(nextLoaderID), c);
            activeServices = activeServices.add(service);
            logbook = logbook.add(service);
            numOfLoadersRequired--;
        }
    }
    return logbook;
}

            
