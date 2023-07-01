package com.zeroone.enums;

public enum TicketStatus {

    NEW {
        public String toString() {
            return "New";
        }
    },

    IN_PROGRESS {
        public String toString() {
            return "In progress";
        }
    },

    CLOSED {
        public String toString() {
            return "Closed";
        }
    },

    SUSPENDED {
        public String toString() {
            return "Suspended";
        }
    },

}
