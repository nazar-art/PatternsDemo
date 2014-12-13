package patterns.structural.facade;

class SkiRent {
    public int rentBoots(int feetSize, int skiLevel) {
        return 20;
    }

    public int rentSki(int weight, int skiLevel) {
        return 40;
    }

    public int rentPole(int height) {
        return 5;
    }
}

class SkiResortTicketSystem {
    public int buyOneDayTicket() {
        return 120;
    }

    public int buyHalfDayTicket() {
        return 60;
    }
}

class HotelBookingSystem {
    public int bookRoom(int roomQuality) {
        switch (roomQuality) {
            case 3:
                return 250;
            case 4:
                return 500;
            case 5:
                return 900;
            default:
                throw new IllegalArgumentException(String.format("roomQuality should be in range [3, 5] - instead of: %d", roomQuality));
        }
    }
}

class SkiResortFacade {
    private SkiRent skiRent = new SkiRent();
    private SkiResortTicketSystem ticketSystem = new SkiResortTicketSystem();
    private HotelBookingSystem hotelBookingSystem = new HotelBookingSystem();

    public int haveGoodRest(int height, int weight, int feetSize, int skierLevel, int roomQuality)
    {
        int skiPrice = skiRent.rentSki(weight, skierLevel);
        int skiBootsPrice = skiRent.rentBoots(feetSize, skierLevel);
        int polePrice = skiRent.rentPole(height);
        int oneDayTicketPrice = ticketSystem.buyOneDayTicket();
        int hotelPrice = hotelBookingSystem.bookRoom(roomQuality);

        return skiPrice + skiBootsPrice + polePrice + oneDayTicketPrice + hotelPrice;
    }

    public int haveRestWithOwnSkis()
    {
        return ticketSystem.buyOneDayTicket();
    }
}

public class FacadeDemo {
    public static void main(String[] args) {
        SkiResortFacade skiResortFacade = new SkiResortFacade();
        int weekendRestPrice = skiResortFacade.haveGoodRest(175, 60, 42, 2, 3);
        System.out.printf("final price: %d%n", weekendRestPrice);
        int haveOwnSki = skiResortFacade.haveRestWithOwnSkis();
        System.out.printf("final price with own ski: %d%n", haveOwnSki);
    }
}
