package pl.mareksowa.model.dao.impl;

import pl.mareksowa.model.Shell;
import pl.mareksowa.model.ShellProgram;
import pl.mareksowa.model.dao.ShellDao;
import pl.mareksowa.view.SimpleConsole;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShellDaoImpl implements ShellDao {

    private SimpleConsole console;
    private int treeCounter;
    private List<Shell>shellList;

    public ShellDaoImpl() {
        shellList = new ArrayList<>();
        shellList.add(new Shell(System.getProperty("user.dir"),"$", false ));
        console = new SimpleConsole();
    }

    //prompt command
    @Override
    public void promptCommand(String input){
        if (input.length()==12&& input.substring(7,12).equals("reset")){
            promptReset();
            shellList.get(0).setPromptCtSuccess(shellList.get(0).getPromptCtSuccess()+1);
        } else if (input.length()==11&& input.substring(7,11).equals("$cwd")){
            shellList.get(0).setPromptDirectory(true);
            shellList.get(0).setPromptCtSuccess(shellList.get(0).getPromptCtSuccess()+1);
        } else {
            shellList.get(0).setPrompt(input.substring(7, input.length()));
            shellList.get(0).setPromptCtSuccess(shellList.get(0).getPromptCtSuccess()+1);
        }
    }

    @Override
    public String updatePrompt(Shell shell) {
        if (shellList.get(0).isPromptDirectory()){
            shellList.get(0).setPrompt(shellList.get(0).getDirectory());
        }
        return shellList.get(0).getPrompt() + ">";
    }

    @Override
    public void promptReset() {
        shellList.get(0).setPrompt("$");
        shellList.get(0).setPromptDirectory(false);
    }

    //cd command
    @Override
    public void cdCommand(String input){
        if (input.length()==5&& input.substring(3,5).equals("..")) {
            if (shellList.get(0).getDirectory().contains("\\")) {
                shellList.get(0).setCdCtSuccess(shellList.get(0).getCdCtSuccess()+1);
                shellList.get(0).setDirectory(shellList.get(0).getDirectory().substring(
                        0, shellList.get(0).getDirectory().lastIndexOf("\\")));
            } else {
                shellList.get(0).setCdCtFail(shellList.get(0).getCdCtFail()+1);
                unknownCommand();
            }
        } else {
            if (checkIfDirIsCorrected(input.substring(3, input.length()))){
                shellList.get(0).setCdCtSuccess(shellList.get(0).getCdCtSuccess()+1);
                shellList.get(0).setDirectory(new StringBuilder().append(shellList.get(0).getDirectory() +
                        "\\" + input.substring(3, input.length())).toString());
            } else {
                shellList.get(0).setCdCtFail(shellList.get(0).getCdCtFail()+1);
                unknownCommand();
            }
        }
    }

    //dir command
    @Override
    public void dirCommand(){
        shellList.get(0).setCurrentDirectory(new File(shellList.get(0).getDirectory()));
        console.print("Content of " + shellList.get(0).getDirectory());
        File[] filesList = shellList.get(0).getCurrentDirectory().listFiles();
        for(File f : filesList){
            if(f.isDirectory())
                console.print("DIR      " + f.getName());
            if(f.isFile()){
                console.print("FILE     " + f.getName());
            }
        }
    }

    //tree command
    @Override
    public void treeCommand(File folder){
        treeCounter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        showDirectoryTree(folder, treeCounter, stringBuilder);
        console.print(stringBuilder.toString());

    }

    private void showDirectoryTree(File folder, int treeCounter,StringBuilder stringBuilder){
        stringBuilder.append(getCounterString(treeCounter));
        stringBuilder.append(folder.getName());
        stringBuilder.append("\n");
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                showDirectoryTree(file, treeCounter + 1, stringBuilder);
            }
        }
    }

    private String getCounterString(int treeCounter) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < treeCounter; i++) {
            stringBuilder.append("-");
        }
        return stringBuilder.toString();
    }

    //statistic command
    @Override
    public void statisticCommand(){
        console.print("cd:" + shellList.get(0).getCdCtSuccess() + ":" + shellList.get(0).getCdCtFail());
        console.print("tree:" + shellList.get(0).getTreeCtSuccess() + ":" + shellList.get(0).getTreeCtFail());
        console.print("dir:" + shellList.get(0).getDirCtSuccess() + ":" + shellList.get(0).getDirCtFail());
        console.print("prompt:" + shellList.get(0).getPromptCtSuccess() + ":" + shellList.get(0).getPromptCtFail());
        console.print("statistics:" + shellList.get(0).getStatisticCtSuccess() + ":" + shellList.get(0).getStatisticCtFail());
    }

    //exit command
    @Override
    public void exitCommand(){
        console.print("Bye");
        System.exit(0);
    }

    //unknown command
    @Override
    public void unknownCommand(){
        console.print("unknown command...");
    }

    @Override
    public List<Shell> getAllShells() {
        return shellList;
    }

    private boolean checkIfDirIsCorrected(String userCdInput){
        ArrayList<String>listOfDir = (ArrayList<String>) getDir(shellList.get(0).getCurrentDirectory());
        for (String dir : listOfDir) {
            if (dir.equals(userCdInput)){
                return true;
            }
        }
        return false;
    }

    private List<String> getDir(File currentDirectory){
        File[] filesList = currentDirectory.listFiles();
        ArrayList<String>listOfDir = new ArrayList<>();
        for(File f : filesList){
            if(f.isDirectory())
                listOfDir.add(f.getName());
        }
        return listOfDir;
    }

}
