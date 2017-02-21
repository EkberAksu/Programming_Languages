/**************************************************
 * written by AIKEBOER AIZEZI
 * id: 131044086
 **************************************************/
package coffee.project;

import coffee.IdentifierList;
import coffee.TokenList;
import coffee.datatypes.*;

import java.util.*;


public class Parser {
    ArrayList<String> rules = new ArrayList<String>();
    ArrayList<String> tokens = new ArrayList<String>();
    ArrayList<ArrayList<String>> outPuts = new ArrayList<ArrayList<String>>();


    public static boolean setParenth(ArrayList<String> tokenStrings) {
        int i = 0;
        Stack s = new Stack();
        String curParenth;
        String prevPrenth;
        boolean flag = true;

        // Check all elements
        while( i < tokenStrings.size())
        {
            // If we find parentheses element
            if(tokenStrings.get(i).equals("'(") || tokenStrings.get(i).equals("(") || tokenStrings.get(i).equals(")"))
            {
                curParenth = tokenStrings.get(i);

                // If the current element is opening parenthese
                if(curParenth.equals("(") || curParenth.equals("'("))
                {
                    s.push(curParenth);
                }
                // If the current element is closing parenthese
                else if(curParenth.equals(")"))
                {
                    // If the stack isn't containing any opening parenthese
                    if(s.isEmpty())
                    {
                        flag = false;
                        break;
                    }
                    else // There are elements in the stack
                    {
                        // Get the last element of the stack
                        // It should be "(" or "'("
                        prevPrenth = (String)s.pop();
                        if(!(prevPrenth.equals("(") || prevPrenth.equals("'(")))
                        { // If not
                            flag = false;
                            break;
                        }
                    }

                }

            }
            ++i;
        }

        // If there is any elements in the stack remaining
        if(!s.isEmpty())
            flag = false;

        return flag;
    }
    public void addAllTheRules(){

        rules.add("( IDS )");
        rules.add("( IDS Id )");
        rules.add("( Id )");
        rules.add(" EXPI ");
        rules.add(" START ");
        rules.add(" INPUT ");
        rules.add(" EXPI ");
        rules.add(" EXPB ");
        rules.add(" EXPLISTI ");

        rules.add("( + EXPI EXPI )");
        rules.add("( - EXPI EXPI )");
        rules.add("( * EXPI EXPI )");
        rules.add("( / EXPI EXPI )");
        rules.add(" Id ");
        rules.add(" IntegerValue ");

        rules.add("( set Id EXPI )");
        rules.add("( deffun Id IDLIST EXPLISTI )");

        rules.add("( Id EXPLISTI )");

        rules.add("( if EXPB then EXPLISTI else EXPLISTI )");
        rules.add("( if EXPB EXPLISTI )");
        rules.add("( if EXPB EXPLISTI EXPLISTI )");
        rules.add("( while (EXPB) EXPLISTI )");
        rules.add("( for ( Id EXPI EXPI ) EXPLISTI )");

        rules.add("( defvar Id EXPI )");

        rules.add("( set Id EXPI )");

        rules.add("( and EXPB EXPB )");
        rules.add("( or EXPB EXPB )");
        rules.add("( not EXPB )");
        rules.add("( equal EXPB EXPB )");
        rules.add("( equal EXPI EXPI )");
        rules.add(" BinaryValue ");

        rules.add("( concat EXPLISTI EXPLISTI )");
        rules.add("( append EXPI EXPLISTI )");
        rules.add(" LISTVALUE ");

        rules.add(" null ");

        rules.add(" '( VALUES ) ");
        rules.add(" '( ) ");

        rules.add(" VALUES IntegerValue ");

    }
    public ArrayList<String> token(TokenList list){
        ArrayList<String> arr = new ArrayList<String>();

        Token.Type currentTokenType;
        for(Token currToken : list.getAllTokens()) {
            currentTokenType = currToken.getTokenType();

            if (currentTokenType == Token.Type.IDENTIFIER) {
                arr.add("Id");

            } else if (currentTokenType == Token.Type.OPERATOR) {
                Operator tempOp = (Operator) currToken;
                arr.add(tempOp.getOperator());

            } else if (currentTokenType == Token.Type.BINARY_VALUE) {
                arr.add("BinaryValue");

            } else if (currentTokenType == Token.Type.INTEGER_VALUE) {
                arr.add("IntegerValue");

            } else if (currentTokenType == Token.Type.KEYWORD) {
                Keyword tempK = (Keyword) currToken;
                arr.add(tempK.getKeyword());

            }
        }
        return arr;
    }
    public boolean isValues(ArrayList<String> temp, int Index){
        if(temp.get(Index+1).equals("VALUES") && temp.get(Index+2).equals(")"))
          return true;
        return false;
    }
    public boolean isExpi(ArrayList<String> temp){
        if(temp.get(0).equals("EXPI") || temp.get(0).equals("EXPLISTI"))
            return true;
        return false;
    }
    public boolean isIntValues(ArrayList<String> temp, int Index){
        if(temp.get(Index+1).equals("VALUES") && temp.get(Index+2).equals("IntegerValue"))
            return true;
        return false;
    }
    public boolean isIntegerValues(ArrayList<String> temp, int Index){
        if(temp.get(Index+1).equals("IntegerValue"))
            return true;
        return false;
    }
    public boolean isRightParenth(ArrayList<String> temp, int Index){
        if(temp.get(Index+1).equals(")"))
            return true;
        return false;
    }
    public boolean isKeyOp(ArrayList<String> temp, int index){
        if(temp.get(index+1).equals("and") || temp.get(index+1).equals("or") ||
                temp.get(index+1).equals("equal") || temp.get(index+1).equals("not") ||
                temp.get(index+1).equals("+") || temp.get(index+1).equals("-") ||
                temp.get(index+1).equals("*") || temp.get(index+1).equals("/") ||
                temp.get(index+1).equals("append"))
            return true;
        return false;
    }
    public boolean isID(ArrayList<String> temp, int Index){
        if(temp.get(Index).equals("Id"))
            return true;
        return false;
    }
    public boolean isBinaryValue(ArrayList<String> temp, int Index){
        if(temp.get(Index).equals("BinaryValue"))
            return true;
        return false;
    }
    public boolean isExpbPar(ArrayList<String> temp, int Index){
        if(((temp.get(Index+2).equals("EXPB") && temp.get(Index+3).equals("EXPB"))||
                (temp.get(Index+2).equals("EXPI") && temp.get(Index+3).equals("EXPI"))) &&
                temp.get(Index+4).equals(")"))
            return true;
        return false;
    }
    public boolean isEXPB(ArrayList<String> temp, int Index){
        if(temp.get(Index+2).equals("EXPB") && temp.get(Index+3).equals("EXPB") &&
                temp.get(Index+4).equals(")"))
            return true;
        return false;
    }
    public boolean isEXPI(ArrayList<String> temp, int Index){
        if(temp.get(Index+2).equals("EXPI") && temp.get(Index+3).equals("EXPI") &&
                temp.get(Index+4).equals(")"))
            return true;
        return false;
    }
    public boolean isExpilist(ArrayList<String> temp, int Index){
        if(temp.get(Index+2).equals("EXPLISTI") && temp.get(Index+3).equals("EXPLISTI") &&
                temp.get(Index+4).equals(")"))
            return true;
        return false;
    }
    public boolean isEqual(ArrayList<String> temp, int Index){
        if(temp.get(Index+1).equals("equal"))
            return true;
        return false;
    }
    public boolean isAndOr(ArrayList<String> temp, int Index){
        if(temp.get(Index+1).equals("and") || temp.get(Index+1).equals("or"))
            return true;
        return false;
    }
    public boolean isNot(ArrayList<String> temp, int Index){
        if(temp.get(Index+1).equals("not"))
            return true;
        return false;
    }
    public boolean isExPar(ArrayList<String> temp, int Index){
        if((temp.get(Index+2).equals("EXPB")) &&
                temp.get(Index+3).equals(")"))
            return true;
        return false;
    }
    public boolean isOps(ArrayList<String> temp, int Index){
        if((temp.get(Index+1).equals("+") || temp.get(Index+1).equals("-") ||
                temp.get(Index+1).equals("*") || temp.get(Index+1).equals("/")))
            return true;
        return false;
    }
    public boolean isListValue(ArrayList<String> temp, int Index){
        if(temp.get(Index).equals("LISTVALUE"))
            return true;
        return false;
    }
    public boolean isConcat(ArrayList<String> temp, int Index){
        if(temp.get(Index+2).equals("EXPLISTI") && temp.get(Index+3).equals("EXPLISTI") &&
                temp.get(Index+4).equals(")"))
            return true;
        return false;
    }
    public boolean isExpExplist(ArrayList<String> temp, int Index){
        if(temp.get(Index+2).equals("EXPI") && temp.get(Index+3).equals("EXPLISTI") &&
                temp.get(Index+4).equals(")"))
            return true;
        return false;
    }
    public boolean isAppend(ArrayList<String> temp, int Index){
        if(temp.get(Index+1).equals("append"))
            return true;
        return false;
    }
    public boolean isIdExp(ArrayList<String> temp, int Index){
        if(temp.get(Index+2).equals("id") && temp.get(Index+3).equals("EXPI") &&
                temp.get(Index+4).equals(")"))
            return true;
        return false;
    }
    public boolean isIdIdlist(ArrayList<String> temp, int Index){
        if( temp.get(Index+2).equals("Id") && temp.get(Index+3).equals("IDLIST") &&
                temp.get(Index+4).equals("EXPLISTI") && temp.get(Index+5).equals(")"))
            return true;
        return false;
    }
    public boolean isdefvar(ArrayList<String> temp, int Index){
        if(temp.get(Index+1).equals("defvar"))
            return true;
        return false;
    }
    public boolean isSet(ArrayList<String> temp, int Index){
        if(temp.get(Index+1).equals("defvar"))
            return true;
        return false;
    }
    public boolean isDeffun(ArrayList<String> temp, int Index){
        if(temp.get(Index+1).equals("defvar"))
            return true;
        return false;
    }

    public ArrayList<String> reduce(ArrayList<String> tokenStrings){
        ArrayList<String> temp = (ArrayList<String>) tokenStrings.clone();

        if(temp.size() == 1){
            // Ends the reducing START -> INPUT
            if(temp.get(0).equals("INPUT"))
                return null;

            // Takes care for INPUT -> EXPI | EXPLISTI
            if(isExpi(temp)) {
                temp.remove(0);
                temp.add("INPUT");
                return temp;
            }

            StringBuilder illigal = new StringBuilder();
            illigal.append("\nilligal[in 0]: Expected -> EXPI | EXPLISI\n");
            illigal.append("\t\t\tReceived  -> ");
            illigal.append(temp.get(0));
            throw new IllegalArgumentException(illigal.toString());
        }

        int index1 = -1;
        int index = -1;
        int lineIndex = -1;

        // Find the indices
        for(int i=0; i<temp.size(); ++i){
            if(temp.get(i).equals("(")){
                index = i;
            }
            if(temp.get(i).equals("'(")){
                lineIndex = i;
            }
            if(temp.get(i).equals(")")){
                index1 = i;
                break;
            }
        }


        if(lineIndex != -1 && lineIndex > index)
        {

            if(isValues(temp, lineIndex)){
                temp.remove(lineIndex); // Remove '(
                temp.remove(lineIndex); // Remove VALUES
                temp.remove(lineIndex); // Remove )
                temp.add(lineIndex, "LISTVALUE"); // Place LISTVALUE
                return temp;
            }

            if(isIntValues(temp, lineIndex)){
                temp.remove(lineIndex+2); // Remove IntegerValue
                return temp;
            }

            if(isIntegerValues(temp, lineIndex)){
                temp.remove(lineIndex+1); // Remove IntegerValue
                temp.add(lineIndex+1, "VALUES"); // Place VALUES
                return temp;
            }

            // Takes care of LISTVALUE -> '()
            if(isRightParenth(temp, lineIndex)){
                temp.remove(lineIndex); // Remove '(
                temp.remove(lineIndex); // Remove )
                temp.add(lineIndex, "LISTVALUE"); // Place LISTVALUE
                return temp;
            }

            StringBuilder illigal = new StringBuilder();
            illigal.append("\nilligal:\n ");
            for(int e=lineIndex; e<index1; ++e){
                illigal.append(temp.get(e) + " ");
            }
            illigal.append(")");
            throw new IllegalArgumentException(illigal.toString());
        }

        // If there is an opened and closed parentheses
        if(index != -1 && index1 != -1)
        {

            if(isKeyOp( temp, index)) {
                for(int i = index+2; i<index1; ++i){
                    if(temp.get(i).equals("IntegerValue")){
                        temp.remove(i);
                        temp.add(i, "EXPI");
                        return temp;
                    }
                    else if(isID( temp, i)){
                        temp.remove(i);
                        temp.add(i, "EXPI");
                        return temp;
                    }
                    else if(isBinaryValue( temp, i)){
                        temp.remove(i);
                        temp.add(i, "EXPB");
                        return temp;
                    }
                }
            }

            if(isEqual( temp, index))
            {
                if(isExpbPar( temp, index))
                {
                    temp.remove(index);
                    temp.remove(index);
                    temp.remove(index);
                    temp.remove(index);
                    temp.remove(index);
                    temp.add(index, "EXPB");
                    return temp;
                }
                else{
                    StringBuilder illigal = new StringBuilder();
                    illigal.append("\nilligal:\n Expected -> (equal EXPB EXPB) | (equal EXPI EXPI)\n");
                    illigal.append("\t\t\tReceived  -> ");
                    illigal.append("(");
                    illigal.append(temp.get(index+1) + " ");
                    illigal.append(temp.get(index+2) + " ");
                    illigal.append(temp.get(index+3) + " ");
                    illigal.append(temp.get(index+4));
                    throw new IllegalArgumentException(illigal.toString());
                }
            }
            else if(isAndOr( temp, index))
            {
                if(isEXPB( temp, index))
                {
                    temp.remove(index); // Remove (
                    temp.remove(index); // Remove And or Or
                    temp.remove(index); // Remove EXPB
                    temp.remove(index); // Remove EXPB
                    temp.remove(index); // Remove )
                    temp.add(index, "EXPB"); // Add EXPB
                    return temp;
                }
                else{
                    StringBuilder illigal = new StringBuilder();
                    illigal.append("\nilligal:\n Expected -> (" + temp.get(index+1));
                    illigal.append(" EXPB EXPB)\n");
                    illigal.append("\t\t\tReceived  -> ");
                    illigal.append("(");
                    illigal.append(temp.get(index+1) + " ");
                    illigal.append(temp.get(index+2) + " ");
                    illigal.append(temp.get(index+3) + " ");
                    illigal.append(temp.get(index+4));
                    throw new IllegalArgumentException(illigal.toString());
                }
            }
            else if(isNot( temp, index))
            {
                if(isExPar( temp, index))
                {
                    temp.remove(index); // Remove (
                    temp.remove(index); // Remove not
                    temp.remove(index); // Remove EXPB
                    temp.remove(index); // Remove )
                    temp.add(index, "EXPB");
                    return temp;
                }
                else{
                    StringBuilder illigal = new StringBuilder();
                    illigal.append("\nilligal:\n ");
                    illigal.append("(");
                    illigal.append(temp.get(index+1) + " ");
                    illigal.append(temp.get(index+2) + " ");
                    illigal.append(temp.get(index+3));
                    throw new IllegalArgumentException(illigal.toString());
                }
            }
            // Takes care of EXPI -> (+ EXPI EXPI) | (- EXPI EXPI) | (* EXPI EXPI) | (/ EXPI EXPI)
            else if(isOps( temp, index))
            {
                if(isEXPI( temp, index))
                {
                    temp.subList(index, index1+1).clear();
                    temp.add(index, "EXPI");
                    return temp;
                }
                else{
                    StringBuilder illigal = new StringBuilder();
                    illigal.append("\nilligal:\n ");
                    illigal.append("(");
                    illigal.append(temp.get(index+1) + " ");
                    illigal.append(temp.get(index+2) + " ");
                    illigal.append(temp.get(index+3) + " ");
                    illigal.append(temp.get(index+4));
                    throw new IllegalArgumentException(illigal.toString());
                }
            }

            for(int i = index+1; i<index1; ++i)
                if(isListValue( temp, i)){
                    temp.remove(i);
                    temp.add(i, "EXPLISTI");
                    return temp;
                }

            if(isConcat( temp, index)){
                if(isExpilist( temp, index))
                {
                    temp.subList(index, index1+1).clear();
                    temp.add(index, "EXPLISTI");
                    return temp;
                }
                else{
                    StringBuilder illigal = new StringBuilder();
                    illigal.append("\nilligal:\n ");
                    illigal.append("(concat ");
                    illigal.append(temp.get(index+2) + " ");
                    illigal.append(temp.get(index+3) + " ");
                    illigal.append(temp.get(index+4));
                    throw new IllegalArgumentException(illigal.toString());
                }
            }

            if(isAppend( temp, index)){
                if(isExpExplist( temp, index))
                {
                    temp.subList(index, index1+1).clear();
                    temp.add(index, "EXPLISTI");
                    return temp;
                }
                else{
                    StringBuilder illigal = new StringBuilder();
                    illigal.append("\nilligal:\n");
                    illigal.append("(");
                    illigal.append(temp.get(index+1) + " ");
                    illigal.append(temp.get(index+2) + " ");
                    illigal.append(temp.get(index+3) + " ");
                    illigal.append(temp.get(index+4));
                    throw new IllegalArgumentException(illigal.toString());
                }
            }

            if(isdefvar( temp, index)){
                if(isIdExp( temp, index))
                {
                    temp.subList(index, index1+1).clear();
                    temp.add(index, "EXPI");
                }
                else{
                    StringBuilder illigal = new StringBuilder();
                    illigal.append("\nilligal:\n");
                    illigal.append("(");
                    illigal.append(temp.get(index+1) + " ");
                    illigal.append(temp.get(index+2) + " ");
                    illigal.append(temp.get(index+3) + " ");
                    illigal.append(temp.get(index+4));
                    throw new IllegalArgumentException(illigal.toString());
                }
            }

            // Takes care of EXPI -> (set Id EXPI)
            if(isSet( temp, index)){
                if(isIdExp( temp, index))
                {
                    temp.subList(index, index1+1).clear();
                    temp.add(index, "EXPI");
                }
                else{
                    StringBuilder illigal = new StringBuilder();
                    illigal.append("\nilligal:\n ");
                    illigal.append("(");
                    illigal.append(temp.get(index+1) + " ");
                    illigal.append(temp.get(index+2) + " ");
                    illigal.append(temp.get(index+3) + " ");
                    illigal.append(temp.get(index+4));
                    throw new IllegalArgumentException(illigal.toString());
                }
            }

            if(isDeffun( temp, index)){

                if(isIdIdlist( temp, index))
                {
                    temp.subList(index, index1+1).clear();
                    temp.add(index, "EXPI");
                }
                else{
                    StringBuilder illigal = new StringBuilder();
                    illigal.append("\nilligal:\n ");
                    illigal.append("(");
                    illigal.append(temp.get(index+1) + " ");
                    illigal.append(temp.get(index+2) + " ");
                    illigal.append(temp.get(index+3) + " ");
                    illigal.append(temp.get(index+4) + " ");
                    illigal.append(temp.get(index+5));
                    throw new IllegalArgumentException(illigal.toString());
                }
            }

        }
        return temp;
    }


    public void parse() {
        IdentifierList identifierList = IdentifierList.getInstance();
        TokenList tokenList = TokenList.getInstance();
        int i = 0;
        addAllTheRules();
        tokens = token(tokenList);


        outPuts.add(tokens);


        int x = 0;
        while(tokens != null && x<50){
            tokens = reduce(tokens);
            if(tokens == null)
                break;

            outPuts.add(tokens);
            ++x;
        }


        Collections.reverse(outPuts);

        // Print the output
        System.out.print("START->");
        while(i<outPuts.size()){

            for(String tokens : outPuts.get(i)){
                System.out.print(" " + tokens);
            }
            System.out.println();
            if(i != outPuts.size()-1)
                System.out.print("\t ->");
            ++i;
        }

    }



}
