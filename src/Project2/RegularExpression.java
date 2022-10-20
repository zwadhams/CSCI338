package Project2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author yaw
 */
public class RegularExpression {

    private String regularExpression;
    private NFA nfa;

    // You are not allowed to change the name of this class or this constructor at all.
    public RegularExpression(String regularExpression) {
        this.regularExpression = regularExpression.replaceAll("\\s+", "");
        nfa = generateNFA();
    }

    // TODO: Complete this method so that it returns the nfa resulting from unioning the two input nfas.
    private NFA union(NFA nfa1, NFA nfa2) {
        return null;
    }

    // TODO: Complete this method so that it returns the nfa resulting from concatenating the two input nfas.
    private NFA concatenate(NFA nfa1, NFA nfa2) {
        // NFA creation example.
        //String[] states = new String[] {"S1", "S2", "S3", "S4"};
        //char[] alphabet = new char[] {'0', '1'};

        // Create HashMap of all transitions in NFA.
        //HashMap<String, HashMap<Character, HashSet<String>>> transitions = new HashMap<>();

        HashMap<String, HashMap<Character, HashSet<String>>> nfa1Transitions = nfa1.getTransitions();
        HashMap<String, HashMap<Character, HashSet<String>>> nfa2Transitions = nfa2.getTransitions();

        //make transition from accept states of nfa1 to start state of nfa2
        //but with what alphabet characters?

        // Create all transitions from S1 with character 0. (There is only one - to S2)
        //HashMap<Character, HashSet<String>> transition = new HashMap<>();
        //transition.put('0', new HashSet<>(Arrays.asList("S2")));
        //transitions.put("S1", transition);

        //work begins here
        String[] states = new String[nfa1.getStates().length + nfa2.getStates().length];
        int numStates = states.length;
        for (int i = 0; i < numStates; i++) {
            states[i] = "S" + (i+1);
        }

        char[] alphabet = {'0', '1'}; //should stay the same??
        //maybe needed below??
        //char[] alphabet = Chars.concat(nfa1.getAlphabet(), nfa2.getAlphabet());

        //transitions go here
        HashMap<String, HashMap<Character, HashSet<String>>> transitions = new HashMap<>();
        HashMap<Character, HashSet<String>> transition = new HashMap<>();

        String startingState = nfa1.getStartState();
        String[] acceptStates = nfa2.getAcceptStates(); //hold up might need work

        //NFA resultingNFA = new NFA(states, alphabet, transitions, startingState, acceptStates);
        //return resultingNFA;
        return null;
    }

    // TODO: Complete this method so that it returns the nfa resulting from "staring" the input nfa.
    private NFA star(NFA nfa) {
        //getting stuff from input nfa
        String[] states = nfa.getStates(); //shouldn't be affected
        char[] alphabet = nfa.getAlphabet(); //shouldn't be affected

        HashMap<String, HashMap<Character, HashSet<String>>> transitions = nfa.getTransitions();

        String startState = nfa.getStartState(); //shouldn't be affected
        //adds the start state to the accept states
        String[] acceptStates = new String[nfa.getAcceptStates().length + 1];
        acceptStates = nfa.getAcceptStates();
        acceptStates[-1] = startState;


        NFA resultingNfa = new NFA(states, alphabet, transitions, startState, acceptStates);
        return resultingNfa;
    }

    // TODO: Complete this method so that it returns the nfa resulting from "plussing" the input nfa.
    private NFA plus(NFA nfa) {
        //getting stuff from input nfa
        String[] states = nfa.getStates();
        char[] alphabet = nfa.getAlphabet(); //shouldn't be affected

        HashMap<String, HashMap<Character, HashSet<String>>> transitions = nfa.getTransitions();

        String startState = nfa.getStartState(); //shouldn't be affected
        //adds the start state to the accept states
        String[] acceptStates = new String[nfa.getAcceptStates().length + 1];
        acceptStates = nfa.getAcceptStates();
        //acceptStates[-1] = startState;

        //working on plussing it


        //NFA nfa1 = new NFA(states, alphabet, transitions, startState, acceptStates);
        //System.out.println(nfa);
        NFA resultingNfa = new NFA(states, alphabet, transitions, startState, acceptStates);
        return resultingNfa;
    }

    // TODO: Complete this method so that it returns the nfa that only accepts the character c.
    private NFA singleCharNFA(char c) {
        // NFA creation example.
        //String[] states = new String[] {"S1", "S2", "S3", "S4"};
        //char[] alphabet = new char[] {'0', '1'};

        // Create HashMap of all transitions in NFA.
        //HashMap<String, HashMap<Character, HashSet<String>>> transitions = new HashMap<>();

        // Create all transitions from S1 with character 0. (There is only one - to S2)
        //HashMap<Character, HashSet<String>> transition = new HashMap<>();
        //transition.put('0', new HashSet<>(Arrays.asList("S2")));
        //transitions.put("S1", transition);

        String[] states = {"S1", "S2"};
        char[] alphabet = new char[] {'0', '1'};

        HashMap<String, HashMap<Character, HashSet<String>>> transitions = new HashMap<>(); //starting state, what numbers (1,0_),m ending states
        HashMap<Character, HashSet<String>> transition = new HashMap<>();
        if (c == '0') {
            transition.put('0', new HashSet<>(Arrays.asList("S2")));
            transitions.put("S1", transition);
        } else if (c == '1') {
            transition.put('1', new HashSet<>(Arrays.asList("S2")));
            transitions.put("S1", transition);
        }

        String startState = "S1";
        String[] acceptStates = {"S2"};

        NFA nfa1 = new NFA(states, alphabet, transitions, startState, acceptStates);

        return nfa1;
    }

    // You are not allowed to change this method's header at all.
    public boolean test(String string) {
        return nfa.accepts(string);
    }

    // Parser. I strongly recommend you do not change any code below this line.
    // Do not change any of the characters recognized in the regex (e.g., U, *, +, 0, 1)
    private int position = -1, ch;

    public NFA generateNFA() {
        nextChar();
        return parseExpression();
    }

    public void nextChar() {
        ch = (++position < regularExpression.length()) ? regularExpression.charAt(position) : -1;
    }

    public boolean eat(int charToEat) {
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    public NFA parseExpression() {
        NFA nfa = parseTerm();
        while (true) {
            if (eat('U')) {
                // Create the nfa that is the union of the two passed nfas.
                nfa = union(nfa, parseTerm());
            } else {
                return nfa;
            }
        }
    }

    public NFA parseTerm() {
        NFA nfa = parseFactor();
        while (true) {
            // Concatenate NFAs.
            if (ch == '0' || ch == '1' || ch == '(') {
                // Create the nfa that is the concatentaion of the two passed nfas.
                nfa = concatenate(nfa, parseFactor());
            } else {
                return nfa;
            }
        }
    }

    public NFA parseFactor() {
        NFA nfa = null;
        if (eat('(')) {
            nfa = parseExpression();
            if (!eat(')')) {
               throw new RuntimeException("Missing ')'");
            }
        } else if (ch == '0' || ch == '1') {
            // Create the nfa that only accepts the character being passed (regularExpression.charAt(position) == '0' or '1').
            nfa = singleCharNFA(regularExpression.charAt(position));
            nextChar();
        }

        if (eat('*')) {
            // Create the nfa that is the star of the passed nfa.
            nfa = star(nfa);
        } else if (eat('+')) {
            // Create the nfa that is the plus of the passed nfa.
            nfa = plus(nfa);
        }

        return nfa;
    }
}
