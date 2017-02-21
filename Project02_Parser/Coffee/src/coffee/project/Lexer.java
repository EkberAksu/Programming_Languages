/**************************************************
 * written by AIKEBOER AIZEZI
 * id: 131044086
 **************************************************/
package coffee.project;

import coffee.IdentifierList;
import coffee.REPL;
import coffee.TokenList;
import coffee.datatypes.*;
import coffee.syntax.Keywords;
import coffee.syntax.Operators;

import java.util.StringTokenizer;
import java.util.ArrayList;

public class Lexer implements REPL.LineInputCallback{
    @Override
    public String lineInput(String line) throws WrongInputException{

        try {
            TokenList token_list = TokenList.getInstance();
            IdentifierList identifier_list = IdentifierList.getInstance();
            ArrayList<String> temp = new ArrayList<String>();
            StringTokenizer token = new StringTokenizer(line, " ()",true);

            int i=0;
            while(token.hasMoreTokens()){
                String currentToken = token.nextToken();
                if(currentToken.equals(" ") || currentToken.equals("\t") || currentToken.equals("\n"))
                    continue;

                temp.add(currentToken);
            }

            String current, next;
            while (i<temp.size()-1) {
                current = temp.get(i);
                next = temp.get(i+1);
                if(current.equals("'") && next.equals("(")){
                    temp.remove(i);
                    temp.remove(i);
                    temp.add(i, "'(");
                }
                ++i;
            }
            for(String nextToken : temp){


                //String nextToken = token.nextToken();
                if(nextToken.equals(" ") || nextToken.equals("\t") || nextToken.equals("\n"))
                    continue;
                /*********************OPERATORS***************************/
                else if (nextToken.equals(Operators.LEFT_PARENTHESIS)){
                    token_list.addToken(new Operator(Operators.LEFT_PARENTHESIS));
                    nextToken.replace("(","");//remove "(" from the token string
                }
                else if (nextToken.equals(Operators.RIGHT_PARENTHESIS)) {
                    token_list.addToken(new Operator(Operators.RIGHT_PARENTHESIS));
                    nextToken.replace(")","");//remove "(" from the token string
                }
                else if (nextToken.equals(Operators.PLUS)) {
                    token_list.addToken(new Operator(Operators.PLUS));
                }
                else if (nextToken.equals(Operators.MINUS)) {
                    token_list.addToken(new Operator(Operators.MINUS));
                }
                else if (nextToken.equals(Operators.SLASH)) {
                    token_list.addToken(new Operator(Operators.SLASH));
                }
                else if (nextToken.equals(Operators.ASTERISK)) {
                    token_list.addToken(new Operator(Operators.ASTERISK));
                }
                else if (nextToken.equals(Operators.QUOT)) {
                    token_list.addToken(new Operator(Operators.QUOT));
                }
                /*********************KEYWORDS***************************/
                else if (nextToken.equals(Keywords.AND)) {
                    token_list.addToken(new Keyword(Keywords.AND));
                }
                else if (nextToken.equals(Keywords.OR)) {
                    token_list.addToken(new Keyword(Keywords.OR));
                }
                else if (nextToken.equals(Keywords.NOT)) {
                    token_list.addToken(new Keyword(Keywords.NOT));
                }
                else if (nextToken.equals(Keywords.EQUAL)) {
                    token_list.addToken(new Keyword(Keywords.EQUAL));
                }
                else if (nextToken.equals(Keywords.APPEND)) {
                    token_list.addToken(new Keyword(Keywords.APPEND));
                }
                else if (nextToken.equals(Keywords.CONCAT)) {
                    token_list.addToken(new Keyword(Keywords.CONCAT));
                }
                else if (nextToken.equals(Keywords.SET)) {
                    token_list.addToken(new Keyword(Keywords.SET));
                }
                else if (nextToken.equals(Keywords.DEFFUN)) {
                    token_list.addToken(new Keyword(Keywords.DEFFUN));
                }
                else if (nextToken.equals(Keywords.FOR)) {
                    token_list.addToken(new Keyword(Keywords.FOR));
                }
                else if (nextToken.equals(Keywords.WHILE)) {
                    token_list.addToken(new Keyword(Keywords.WHILE));
                }
                else if (nextToken.equals(Keywords.IF)) {
                    token_list.addToken(new Keyword(Keywords.IF));
                }
                else if (nextToken.equals(Keywords.THEN)) {
                    token_list.addToken(new Keyword(Keywords.THEN));
                }
                else if (nextToken.equals(Keywords.ELSE)) {
                    token_list.addToken(new Keyword(Keywords.ELSE));
                }
                /*********************Valuebinary***************************/
                else if (nextToken.equals(Keywords.TRUE)) {
                    token_list.addToken(new ValueBinary(true));
                }
                else if (nextToken.equals(Keywords.FALSE)) {
                    token_list.addToken(new ValueBinary(false));
                }
                else {
                    /*********************IDENTIFIER***************************/
                    //Used DFA to check if this token is an identifier
                    //dfa is implemented according to  the syntax grammar of Identifier
                    char ch = nextToken.charAt(0);    //System.out.println(ch);
                    if (Character.isLetter(ch)) {
                        for (i = 1; i < nextToken.length(); ++i) {
                            ch = nextToken.charAt(i);
                            if (!Character.isLetter(ch)) { //error detected, an exception is throwed
                                throw new WrongInputException("invalid token");
                            }
                        }

                        identifier_list.addIdentifier(nextToken);
                        token_list.addToken(new Identifier(nextToken));
                    } else if (ch == '-' || Character.isDigit(ch)) {

                        /*********************INTEGER***************************/
                        //Used DFA to check if this token is an Value Int
                        //dfa is implemented according to  the syntax grammar of id
                        if (ch == '-' && nextToken.charAt(1) == '0')
                            //error detected, an exception is throwed
                            throw new WrongInputException("invalid token");
                        for (i = 1; i < nextToken.length(); ++i) {
                            ch = nextToken.charAt(i);    //System.out.println(ch);

                            if (!Character.isDigit(ch)) {
                                //error detected, an exception is throwed
                                throw new WrongInputException("invalid token");
                            }

                        }

                        token_list.addToken(new ValueInt(Integer.parseInt(nextToken)));
                    } else {
                        //error detected, an exception is throwed
                        throw new WrongInputException("invalid token");
                    }
                }
                /********************************************************************/

            }

        } catch (WrongInputException e) {     ////catch expection
            e.printStackTrace();
        }

        return null;

    }
    //A NEW EXCEPTION IS DEFINED
    public static class WrongInputException extends Exception {
        private String s;

        public WrongInputException(String s) {
            super(s);
        }
    }


}
