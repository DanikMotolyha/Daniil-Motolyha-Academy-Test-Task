public class Intervals {

    private static String[][] intervals = {
            {"m2", "1", "2"},
            {"M2", "2", "2"},
            {"m3", "3", "3"},
            {"M3", "4", "3"},
            {"P4", "5", "4"},
            {"P5", "7", "5"},
            {"m6", "8", "6"},
            {"M6", "9", "6"},
            {"m7", "10", "7"},
            {"M7", "11", "7"},
            {"P8", "12", "8"}};

    private static String[] semitone = {
            "C", "-", "-", "D", "-", "-", "E", "-", "F", "-",
            "-", "G", "-", "-", "A", "-", "-", "B", "-", "C"};
    private static String[] intervalConstructionNotes = {
            "Cb", "C", "C#", "Db", "D", "D#", "Eb",
            "E", "E#", "Fb", "F", "F#", "Gb", "G",
            "G#", "Ab", "A", "A#", "Bb", "B", "B#"};
    private static String[] intervalIdentificationNotes = {
            "Cbb", "Cb", "C", "C#", "C##", "Dbb", "Db", "D", "D#",
            "D##", "Ebb", "Eb", "E", "E#", "E##", "Fbb", "Fb", "F",
            "F#", "F##", "Gbb", "Gb", "G", "G#", "G##", "Abb", "Ab",
            "A", "A#", "A##", "Bbb", "Bb", "B", "B#", "B##"};


    public static String intervalConstruction(String[] args) {
        StringBuilder result = new StringBuilder();
        try {
            boolean isExistInterval = false;
            boolean isExistNote = false;
            String[] interval = new String[0];
            for (String[] item : intervals) {
                if (item[0].equals(args[0])) {
                    isExistInterval = true;
                    interval = item;
                    break;
                }
            }
            for (String item : intervalConstructionNotes) {
                if (item.equals(args[1])) {
                    isExistNote = true;
                    break;
                }
            }
            if (args.length < 2 || args.length > 3) {
                throw new Exception("Illegal number of elements in input array ");
            }
            if (!isExistInterval) {
                throw new Exception("Incorrect first parameter ");
            }
            if (!isExistNote) {
                throw new Exception("Incorrect second parameter ");
            }
            if (args.length == 3 && !args[2].equals("asc") && !args[2].equals("dsc")) {
                throw new Exception("Incorrect third parameter ");
            }
            int from = 0;
            for (int i = 0; i < semitone.length; i++) {
                if (semitone[i].equals(args[1].substring(0, 1))) {
                    from = i;
                    break;
                }
            }
            int degrees = Integer.parseInt(interval[2]);
            int semitoneCounter = 0;
            if (args.length == 3 && args[2].equals("dsc")) {
                if (args[1].length() != 1) {
                    String semitones = args[1].substring(1);
                    if (semitones.substring(0, 1).equals("b")) {
                        semitoneCounter -= semitones.length();
                    } else {
                        semitoneCounter += semitones.length();
                    }
                }
                while (degrees != 0) {
                    if (from == 0) {
                        from = semitone.length - 1;
                    }
                    if (semitone[from--].equals("-")) {
                        semitoneCounter++;
                    } else {
                        degrees--;
                    }
                }
                result.append(semitone[from + 1]);
                semitoneCounter -= Integer.parseInt(interval[1]);
                while (semitoneCounter != 0) {
                    if (semitoneCounter < 0) {
                        result.append('b');
                        semitoneCounter++;
                    }
                    if (semitoneCounter > 0) {
                        result.append('#');
                        semitoneCounter--;
                    }
                }
            } else {
                if (args[1].length() != 1) {
                    String semitones = args[1].substring(1);
                    if (semitones.substring(0, 1).equals("b")) {
                        semitoneCounter += semitones.length();
                    } else {
                        semitoneCounter -= semitones.length();
                    }
                }
                while (degrees != 0) {
                    if (from == semitone.length - 1) {
                        from = 0;
                    }
                    if (semitone[from++].equals("-")) {
                        semitoneCounter++;
                    } else {
                        degrees--;
                    }
                }
                result.append(semitone[from - 1]);
                semitoneCounter -= Integer.parseInt(interval[1]);
                while (semitoneCounter != 0) {
                    if (semitoneCounter > 0) {
                        result.append('b');
                        semitoneCounter--;
                    }
                    if (semitoneCounter < 0) {
                        result.append('#');
                        semitoneCounter++;
                    }
                }
            }
            return result.toString();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    public static String intervalIdentification(String[] args) {
        try {
            boolean isExistFirstNote = false;
            boolean isExistSecondNote = false;
            for (String item : intervalIdentificationNotes) {
                if(item.equals(args[0])){
                    isExistFirstNote = true;
                }
                if(item.equals(args[1])){
                    isExistSecondNote = true;
                }
            }
            if (args.length < 2 || args.length > 3) {
                throw new Exception("Illegal number of elements in input array ");
            }
            if (!isExistFirstNote) {
                throw new Exception("Incorrect first parameter ");
            }
            if (!isExistSecondNote) {
                throw new Exception("Incorrect second parameter ");
            }
            if (args.length == 3 && !args[2].equals("asc") && !args[2].equals("dsc")) {
                throw new Exception("Incorrect third parameter ");
            }
            int semitoneCounter = 0;
            int from = 0;
            int to = 0;
            for (int i = 0; i < semitone.length; i++) {
                if (semitone[i].equals(args[0].substring(0, 1))) {
                    from = i;
                }
                if (semitone[i].equals(args[1].substring(0, 1))) {
                    to = i;
                }
            }
            if (args.length == 3 && args[2].equals("dsc")) {
                if (args[0].length() != 1) {
                    String semitones = args[0].substring(1);
                    if (semitones.substring(0, 1).equals("b")) {
                        semitoneCounter -= semitones.length();
                    } else {
                        semitoneCounter += semitones.length();
                    }
                }
                if (args[1].length() != 1) {
                    String semitones = args[1].substring(1);
                    if (semitones.substring(0, 1).equals("b")) {
                        semitoneCounter += semitones.length();
                    } else {
                        semitoneCounter -= semitones.length();
                    }
                }
                while (from != to) {
                    if (from == 0) {
                        from = semitone.length - 1;
                    }
                    if (semitone[from].equals("-")) {
                        semitoneCounter++;
                    }
                    from--;
                }
            } else {
                if (args[0].length() != 1) {
                    String semitones = args[0].substring(1);
                    if (semitones.substring(0, 1).equals("b")) {
                        semitoneCounter += semitones.length();
                    } else {
                        semitoneCounter -= semitones.length();
                    }
                }
                if (args[1].length() != 1) {
                    String semitones = args[1].substring(1);
                    if (semitones.substring(0, 1).equals("b")) {
                        semitoneCounter -= semitones.length();
                    } else {
                        semitoneCounter += semitones.length();
                    }
                }
                while (from != to) {
                    if (from == semitone.length - 1) {
                        from = 0;
                    }
                    if (semitone[from].equals("-")) {
                        semitoneCounter++;
                    }
                    from++;
                }
            }
            for (String[] interval : intervals) {
                if (interval[1].equals(Integer.toString(semitoneCounter))) {
                    return interval[0];
                }
            }
            throw new Exception("Cannot identify the interval");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }
}
