package pl.mareksowa.model;

import java.io.File;

public class Shell {

    private String directory;
    private File currentDirectory;
    private String prompt;
    private boolean isPromptDirectory;
    private int cdCtSuccess, cdCtFail, treeCtSuccess, treeCtFail, dirCtSuccess, dirCtFail;
    private int promptCtSuccess, promptCtFail, statisticCtSuccess, statisticCtFail, treeCounter;

    public Shell(String directory, String prompt, boolean isPromptDirectory) {
        this.directory = directory;
        this.prompt = prompt;
        this.isPromptDirectory = isPromptDirectory;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public File getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(File currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public boolean isPromptDirectory() {
        return isPromptDirectory;
    }

    public void setPromptDirectory(boolean promptDirectory) {
        isPromptDirectory = promptDirectory;
    }

    public int getCdCtSuccess() {
        return cdCtSuccess;
    }

    public void setCdCtSuccess(int cdCtSuccess) {
        this.cdCtSuccess = cdCtSuccess;
    }

    public int getCdCtFail() {
        return cdCtFail;
    }

    public void setCdCtFail(int cdCtFail) {
        this.cdCtFail = cdCtFail;
    }

    public int getTreeCtSuccess() {
        return treeCtSuccess;
    }

    public void setTreeCtSuccess(int treeCtSuccess) {
        this.treeCtSuccess = treeCtSuccess;
    }

    public int getTreeCtFail() {
        return treeCtFail;
    }

    public void setTreeCtFail(int treeCtFail) {
        this.treeCtFail = treeCtFail;
    }

    public int getDirCtSuccess() {
        return dirCtSuccess;
    }

    public void setDirCtSuccess(int dirCtSuccess) {
        this.dirCtSuccess = dirCtSuccess;
    }

    public int getDirCtFail() {
        return dirCtFail;
    }

    public void setDirCtFail(int dirCtFail) {
        this.dirCtFail = dirCtFail;
    }

    public int getPromptCtSuccess() {
        return promptCtSuccess;
    }

    public void setPromptCtSuccess(int promptCtSuccess) {
        this.promptCtSuccess = promptCtSuccess;
    }

    public int getPromptCtFail() {
        return promptCtFail;
    }

    public void setPromptCtFail(int promptCtFail) {
        this.promptCtFail = promptCtFail;
    }

    public int getStatisticCtSuccess() {
        return statisticCtSuccess;
    }

    public void setStatisticCtSuccess(int statisticCtSuccess) {
        this.statisticCtSuccess = statisticCtSuccess;
    }

    public int getStatisticCtFail() {
        return statisticCtFail;
    }

    public void setStatisticCtFail(int statisticCtFail) {
        this.statisticCtFail = statisticCtFail;
    }

    public int getTreeCounter() {
        return treeCounter;
    }

    public void setTreeCounter(int treeCounter) {
        this.treeCounter = treeCounter;
    }
}
