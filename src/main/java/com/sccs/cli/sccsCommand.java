package com.sccs.cli;

import java.util.*;
//main function to execute the commands
public interface sccsCommand { //interface
    int execute(List<String> args);
}
