package pl.mareksowa.model;


import pl.mareksowa.controller.UserController;
import pl.mareksowa.model.dao.ShellDao;
import pl.mareksowa.model.dao.impl.ShellDaoImpl;
import pl.mareksowa.view.SimpleConsole;

public class ShellProgram {

    //declarations
    private ShellDao shellDao;
    private Shell shell;
    private UserController userController;
    private SimpleConsole console;
    private String input;

    public void startShellProgram(){

        //initializations
        shellDao = new ShellDaoImpl();
        shell = shellDao.getAllShells().get(0);
        userController = new UserController();
        console = new SimpleConsole();
        input = null;

        //main program
        while (true){
            console.print(shellDao.updatePrompt(shell));
            input = userController.getUserInputString();
            console.print("User said: " + input);

            //check PROMPT
            if (input.length()>7 && input.substring(0, 7).equals("prompt ")){
                shellDao.promptCommand(input);
            } else if (input.length()>3 && input.substring(0, 3).equals("cd ")){
                shellDao.cdCommand(input);
            } else {
                switch (input){
                    case "dir":{
                        shellDao.dirCommand();
                        shell.setDirCtSuccess(shell.getDirCtSuccess()+1);
                        break;
                    }
                    case "tree":{
                        shellDao.treeCommand(shell.getCurrentDirectory());
                        shell.setTreeCtSuccess(shell.getTreeCtSuccess()+1);
                        break;
                    }
                    case "exit":{
                        shellDao.exitCommand();
                        break;
                    }
                    case "statistics":{
                        shell.setStatisticCtSuccess(shell.getStatisticCtSuccess()+1);
                        shellDao.statisticCommand();
                        break;
                    }
                    default:{
                        shellDao.unknownCommand();
                        break;
                    }
                }
            }
        }
    }

}
