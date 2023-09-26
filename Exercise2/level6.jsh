ImList<Service> serveCruises(ImList<Cruise> cruises) {
    ImList<Service> activeServices = new ImList<Service>();
    ImList<Service> expiredServices = new ImList<Service>();
    ImList<Service> logbook = new ImList<Service>();
    int nextLoaderID = 0;

    for (Cruise c : cruises) {
        for (int i = 0; i < activeServices.size(); i++) {
            Service s = activeServices.get(i);
            if (s.getReadyTime() <= c.getArrivalTime()) {
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
             if (nextLoaderID % 3 == 0) {
                service = new Service(new RecycledLoader(nextLoaderID), c);
            }
            activeServices = activeServices.add(service);
            logbook = logbook.add(service);
            numOfLoadersRequired--;
        }
    }
    return logbook;
}


ImList<Service> serveCruises(ImList<Cruise> cruises) {
    ImList<Service> activeServices = new ImList<Service>();
    ImList<Service> expiredServices = new ImList<Service>();
    ImList<Service> logbook = new ImList<Service>();
    ImList<Service> readyServices = new ImList<Service>();
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

        for (Service s : expiredServices) {
            if (s.getReadyTime() <= c.getArrivalTime()) {
                readyServices = readyServices.add(s);
                }
            if (readyServices.size() == c.getNumOfLoadersRequired()) {
                break;
                }
            }

        int numOfLoadersRequired = c.getNumOfLoadersRequired();
        while (numOfLoadersRequired > 0 && !readyServices.isEmpty()) {
            Service service = readyServices.get(0);
            int expiredServicesIndex = expiredServices.indexOf(service);
            expiredServices = expiredServices.remove(expiredServicesIndex);
            readyServices = readyServices.remove(0);
            Loader loader = service.getLoader();
            service = new Service(loader, c);
            activeServices = activeServices.add(service);
            logbook = logbook.add(service);
            numOfLoadersRequired--;
        }
        while (numOfLoadersRequired > 0 ) {
            nextLoaderID += 1;
            Loader nextLoader = new Loader(nextLoaderID);
            if (nextLoaderID % 3 == 0) {
                nextLoader = new RecycledLoader(nextLoaderID);
            }
            Service service = new Service(nextLoader, c);
            activeServices = activeServices.add(service);
            logbook = logbook.add(service);
            numOfLoadersRequired--;
        }
    }
    return logbook;
}



