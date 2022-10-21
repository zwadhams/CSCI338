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

    private int stateCounter = 0;
    char[] alphabet = {'0', '1'};

    // You are not allowed to change the name of this class or this constructor at all.
    public RegularExpression(String regularExpression) {
        this.regularExpression = regularExpression.replaceAll("\\s+", "");
        nfa = generateNFA();
    }

    // TODO: Complete this method so that it returns the nfa resulting from unioning the two input nfas.
    private NFA union(NFA nfa1, NFA nfa2) {
        //count new states
        int n = nfa1.getStates().length + nfa2.getStates().length;
        stateCounter = stateCounter + n + 1;

        //Adjust State Values
        String[] states = new String[n+1];
        for(int i=0; i<n+1; i++){
            states[n-i]="S" + (stateCounter - i);
        }

        //changes last state to new start state
        String startState = states[states.length-1];

        //Add adjusted acceptStates
        int numberToAccept = nfa1.getAcceptStates().length + nfa1.getAcceptStates().length;
        String[] acceptStates = new String[numberToAccept];
        for(int i= 0; i<numberToAccept; i++) {
            String acceptState;
            if(i<nfa1.getAcceptStates().length) {
                acceptState = nfa1.getAcceptStates()[i];
            }
            else{
                acceptState = nfa2.getAcceptStates()[i-nfa1.getAcceptStates().length];
            }
            acceptStates[i] = changeState(acceptState,n);
        }

        //creates transitions
        HashMap<String, HashMap<Character, HashSet<String>>> transitions = new HashMap<>();
        HashMap<Character, HashSet<String>> transition;
        addNewTransitions(nfa1, n, transitions);
        addNewTransitions(nfa2, n, transitions);

        //handles epsilons
        transition = new HashMap<>();
        transition.put('e', new HashSet<>(Arrays.asList(changeState(nfa1.getStartState(), n),changeState(nfa2.getStartState(), n))));
        transitions.put(startState, transition);

        NFA resultingNFA = new NFA(states, alphabet, transitions, startState, acceptStates);

        return resultingNFA;
    }

    // TODO: Complete this method so that it returns the nfa resulting from concatenating the two input nfas.
    private NFA concatenate(NFA nfa1, NFA nfa2) {
        //get number of states
        int n = nfa1.getStates().length + nfa2.getStates().length;
        stateCounter = stateCounter + n;

        //changed the state names
        String[] states = new String[n];
        for(int i=0; i<n; i++){
            states[n-i-1]="S" + (stateCounter - i);
        }

        //Adjust nfa1 start state
        String startState = changeState(nfa1.getStartState(),n);

        //adjusts
        int numAccept = nfa2.getAcceptStates().length;
        String[] acceptStates = new String[numAccept];
        for(int i= 0; i<numAccept; i++) {
            String acceptState = nfa2.getAcceptStates()[i];
            acceptStates[i] = changeState(acceptState,n);
        }

        //adds transitions to main map
        HashMap<String, HashMap<Character, HashSet<String>>> transitions = new HashMap<>();
        HashMap<Character, HashSet<String>> transition;
        addNewTransitions(nfa1, n, transitions);
        addNewTransitions(nfa2, n, transitions);

        //handles epsilon transitions
        for(String oldAcceptState : nfa1.getAcceptStates()){
            String nf1AcceptState = changeState(oldAcceptState,n);
            transition = new HashMap<>();
            transition.put('e' , new HashSet<>(Arrays.asList(changeState(nfa2.getStartState(),n))));
            transitions.put(nf1AcceptState, transition);
        }

        //Construct NFA
        NFA resultingNFA = new NFA(states, alphabet, transitions, startState, acceptStates);

        return resultingNFA;
    }

    private String changeState(String names, int n){
        return "S" + (Integer.valueOf(names.substring(1)) + n);
    }

    private void addNewTransitions(NFA nfa1, int n, HashMap<String, HashMap<Character, HashSet<String>>> transitions) {
        HashMap<Character, HashSet<String>> transition;
        for(String state1 : nfa1.getTransitions().keySet()){
            HashMap<Character, HashSet<String>> state1Trans = nfa1.getTransitions().get(state1);
            for(char event : state1Trans.keySet()){
                for(String state2: state1Trans.get(event)){
                    transition = new HashMap<>();
                    transition.put(event , new HashSet<>(Arrays.asList(changeState(state2, n))));
                    transitions.put(changeState(state1, n), transition);
                }
            }
        }
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

        int n = 2;
        int[] stateCount = new int[n]; //will ALWAYS be at most two long
        for(int i=0; i<n; i++){
            stateCounter++;
            stateCount[i]=stateCounter;
        }

        //NFA Attributes
        String[] states = new String[n];
        for(int i=0; i<n; i++){
            states[i]="S" + Integer.toString(stateCount[i]);
        }

        String startState = states[0]; //stays the same
        String[] acceptStates = new String[]{states[1]};

        //Add transitions
        HashMap<String, HashMap<Character, HashSet<String>>> transitions = new HashMap<>();

        //transition from first state to second using the passed in character
        HashMap<Character, HashSet<String>> transition = new HashMap<>();
        transition.put(c , new HashSet<>(Arrays.asList(states[1])));
        transitions.put(states[0], transition);

        NFA nfa = new NFA(states, alphabet, transitions, startState, acceptStates);

        return nfa;
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
