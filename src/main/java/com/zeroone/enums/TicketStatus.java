package com.zeroone.enums;

public enum TicketStatus {

    NEW {
        public String toString() {
            return "Nowy";
        }
    },

    IN_PROGRESS {
        public String toString() {
            return "W toku";
        }
    },

    CLOSED {
        public String toString() {
            return "Zamknięty";
        }
    },

    SUSPENDED {
        public String toString() {
            return "Wstrzymany";
        }
    },

}
