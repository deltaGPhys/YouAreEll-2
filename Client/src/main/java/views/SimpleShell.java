package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controllers.IdController;
import controllers.MessageController;
import controllers.TransactionController;
import models.Id;
import youareell.YouAreEll;

// Simple Shell is a Console view for youareell.YouAreEll.
public class SimpleShell {


    public static void prettyPrint(String output) {
        // yep, make an effort to format things nicely, eh?

        System.out.println(output);
    }
    public static void main(String[] args) throws java.io.IOException {
        YouAreEll webber = new YouAreEll();
        
        String commandLine;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        //we break out with <ctrl c>
        while (true) {
            //read what the user enters
            System.out.println("cmd? ");
            commandLine = console.readLine();

            //input parsed into array of strings(command and arguments)
            String[] commands = commandLine.split(" ");
            List<String> list = new ArrayList<String>();

            //if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;
            if (commandLine.equals("exit")) {
                System.out.println("bye!");
                break;
            }

            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                //System.out.println(commands[i]); //***check to see if parsing/split worked***
                list.add(commands[i]);

            }
            System.out.println(list); //***check to see if list was added correctly***
            history.addAll(list);
            try {
                //display history of shell with index
                if (list.get(list.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }

                // Specific Commands.

                // ids
                if (list.contains("ids")) {
                    if (list.size() == 1) {
                        // "ids"
                        System.out.println("******************** IDs Found ********************");
                        System.out.println(webber.view_all_ids());
                        System.out.println("***************************************************");

                    } else {

                        if (list.size() == 3 && list.get(1).equals("setCurrent")) {
                            // set current user for msgController
                            System.out.println(webber.setMyId(list.get(2)));
                        } else if (list.size() == 3) {
                            // "ids <name> <gHname>"
                            String name = list.get(1);
                            String gHname = list.get(2);
                            webber.putOrPostId(name,gHname);

                        } else if (list.size() == 2) {
                            // "ids <name>" - get user
                            System.out.println(webber.get_id(list.get(1)));
                        }
                    }

                    continue;
                }

                // messages
                if (list.contains("messages")) {
                    if (list.size() == 1) {
                        // "messages"
                        System.out.println("**************** Last 20 Messages *****************");
                        System.out.println(webber.view_all_messages());
                        System.out.println("***************************************************");

                    } else {
                        if (list.size() == 2) {
                            System.out.println(String.format("************** Messages to %s *************\");", list.get(1)));
                            System.out.println(webber.view_messages_to_user(list.get(1)));
                            System.out.println("***************************************************");
                        }
                    }
                    continue;
                }
                // send messages
                if (list.get(0).equals("send") && collapseMessageInList(new ArrayList<String>(list)).size()<list.size()) {
                    list = collapseMessageInList(new ArrayList<String>(list));
                    if (list.size() == 2) {
                        // "send 'message'"
                        System.out.println(webber.sendMessage(list.get(1)));
                    } else if (list.size() == 3 && !list.contains("to")) {
                        // "send <yourId> 'message'"
                        System.out.println(webber.sendMessage(list.get(1),list.get(2)));

                    } else {
                        if (list.size() == 4 && list.get(2).equals("to")) {
                            // "send 'message' to <recip>"
                            System.out.println(webber.sendMessage("",list.get(3),list.get(1)));
                        } else if (list.size() == 5 && list.get(3).equals("to")) {
                            // "send <yourId> 'message' to <recip>"
                            System.out.println(webber.sendMessage(list.get(1),list.get(4),list.get(2)));
                        }
                    }
                    continue;
                }
                //!! command returns the last command in history
                if (list.get(list.size() - 1).equals("!!")) {
                    pb.command(history.get(history.size() - 2));

                }//!<integer value i> command
                else if (list.get(list.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(list.get(list.size() - 1).charAt(1));
                    if (b <= history.size())//check if integer entered isn't bigger than history size
                        pb.command(history.get(b));
                } else {
                    pb.command(list);
                }

                // wait, wait, what curiousness is this?
                Process process = pb.start();

                //obtain the input stream
                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                //read output of the process
                String line;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
                br.close();


            }

            //catch ioexception, output appropriate message, resume waiting for input
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("Input Error, Please try again!");
            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */
        }
    }

    public static ArrayList<String> collapseMessageInList (ArrayList<String> list) {
        int firstApostropheTerm = -1;
        int secondApostropheTerm = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches("^'.*$")) {
                firstApostropheTerm = i;
                break;
            }
        }
        for (int i = list.size()-1; i >= 0; i--) {
            if (list.get(i).matches("^.*'$") ) {
                secondApostropheTerm = i;
                break;
            }
        }
        if (firstApostropheTerm < 0 || secondApostropheTerm < 0 || secondApostropheTerm < firstApostropheTerm) {
            return list;
        }

        String message = String.join(" ",list.subList(firstApostropheTerm,secondApostropheTerm+1));
        message = message.replace("'","").trim();
        for (int i = firstApostropheTerm; i < secondApostropheTerm; i++ ){
            list.remove(firstApostropheTerm);
        }
        list.set(firstApostropheTerm,message);
        return list;
    }

}