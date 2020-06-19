package com.premierqlibrary.binding.command;

/**
 * Created by xiaodengwen.
 * E-mail: leoan0923@163.com
 * Date: 2020/06/15
 * Desc:绑定命令，onClick
 * */
public class BindingCommand {
    BindingAction execute;

    public BindingCommand(BindingAction execute) {
        this.execute = execute;
    }

    /**
     * 执行BindingAction命令
     */
    public void execute() {
        if (execute != null) {
            execute.call();
        }
    }

}



