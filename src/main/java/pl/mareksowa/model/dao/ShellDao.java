package pl.mareksowa.model.dao;

import pl.mareksowa.model.Shell;

import java.io.File;
import java.util.List;

public interface ShellDao {
    List<Shell> getAllShells();
    void promptCommand(String input);
    String updatePrompt(Shell shell);
    void promptReset();
    void cdCommand(String input);
    void dirCommand();
    void treeCommand(File folder);
    void statisticCommand();
    void exitCommand();
    void unknownCommand();
}
