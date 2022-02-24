package com.iAmLuisJ.utilityClasses;

import java.util.*;

public class Packet {
    // properties
    int length;
    int version;

    public static Packet decode(String message) {
        // first 4 are the version
        String versionString = message.substring(0, 3);
        int version = Integer.parseInt(versionString.substring(0, 3), 2);
        String typeString = message.substring(3, 6);
        int typeID = Integer.parseInt(typeString, 2);
        // if type is 4, return a Literal Packet, otherwise return an Operator Packet
        return typeID == 4 ? Literal.decode(version, message) : Operator.decode(version, typeID, message);
    }

    public int versionSum() {
        return version;
    }

    public long getValue() {
        return 0;
    }
}

class Literal extends Packet {
    long value;

    public static Literal decode(int version, String message) {
        Literal output = new Literal();
        output.version = version;

        StringBuilder builder = new StringBuilder();
        // skip the version and type, set i to 6
        int i = 6;
        while (message.charAt(6) == '1') { // if its a 1, there's another packet inside
            builder.append(message.substring(i + 1, i + 5));
            i += 5;
        } // else its the last packet
        builder.append(message.substring(i + 1, i + 5));
        output.value = Long.parseLong(builder.toString(), 2);
        output.length = 6 + i - 1;
        return output;
    }

    @Override
    public long getValue() {
        return value;
    }
}

class Operator extends Packet {
    int typeID;
    Packet[] packets;

    public static Operator decode(int version, int typeID, String message) {
        // length type id is directly after the typeID
        return message.charAt(6) == '0' ? fifteenBit(version, typeID, message)
                : elevenBit(version, typeID, message);
    }

    // parse a 15 bit operator packet
    public static Operator fifteenBit(int version, int typeID, String message) {
        // next 15 bits describe length of all subpackets
        Operator output = new Operator();
        output.version = version;
        output.typeID = typeID;
        List<Packet> packetList = new ArrayList<>();
        // the length of packets
        int packetsLength = Integer.parseInt(message.substring(7, 7 + 15), 2);
        int packetCursor = 0;
        // while loop on remaining packets
        while (packetCursor < packetsLength) {
            Packet p = Packet.decode(message.substring(packetCursor + 7 + 15));
            packetCursor += p.length;
            packetList.add(p);
        }
        output.packets = packetList.toArray(new Packet[0]);
        output.length = packetsLength + 7 + 15;
        return output;
    }

    // parse an 11 bit operator packet
    public static Operator elevenBit(int version, int typeID, String message) {
        Operator output = new Operator();
        output.version = version;
        output.typeID = typeID;
        // next 11 bits describe number of sub packets
        int packetCount = Integer.parseInt(message.substring(7, 7 + 11), 2);
        int packetCursor = 0;
        output.packets = new Packet[packetCount];
        // for loop on number of packets to parse
        for (int i = 0; i < packetCount; i++) {
            output.packets[i] = Packet.decode(message.substring(packetCursor + 7 + 11));
            packetCursor += output.packets[i].length;
        }
        output.length = packetCursor + 7 + 11;
        return output;
    }

    @Override
    public int versionSum() {
        int sum = 0;
        for (Packet packet : packets) {
            sum += packet.versionSum();
        }
        return sum + version;
    }

    @Override
    public long getValue() {
        switch (typeID) {
            case 0: // sum
                long sum = 0;
                for (Packet p : packets) {
                    sum += p.getValue();
                }
                return sum;
            case 1: // multiply
                long product = 1;
                for (Packet p : packets) {
                    product *= p.getValue();
                }
                return product;
            case 2: // minimum
                long minVal = packets[0].getValue();
                int minIndex = 0;
                for (int i = 1; i < packets.length; i++) {
                    long currentVal = packets[i].getValue();
                    if (currentVal < minVal) {
                        minVal = currentVal;
                        minIndex = i;
                    }
                }
                return packets[minIndex].getValue();
            case 3: // maximum packet
                long maxVal = packets[0].getValue();
                int maxIndex = 0;
                for (int i = 1; i < packets.length; i++) {
                    long currentVal = packets[i].getValue();
                    if (currentVal > maxVal) {
                        maxVal = currentVal;
                        maxIndex = i;
                    }
                }
                return packets[maxIndex].getValue();
            case 5: // greater than packet
                return packets[0].getValue() > packets[1].getValue() ? 1 : 0;
            case 6: // less than packet
                return packets[0].getValue() > packets[1].getValue() ? 1 : 0;
            case 7: // equal to packet
                return packets[0].getValue() > packets[1].getValue() ? 1 : 0;
            default:
                return 0;
        }
    }

}