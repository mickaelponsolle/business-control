package org.business.control.business2.bus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.business.control.business2.command.Command;
import org.business.control.business2.handler.Handler;

public class CommandBus {
    private final Map<Class, Handler> handlersMap;

    public CommandBus(List<? extends Handler> handlers) {
        handlersMap = new HashMap();
        for (Handler handler : handlers) {
            handlersMap.put(handler.listenTo(), handler);
        }
    }

    public <R, C extends Command<R>> R dispatch(C command) {
        for (Handler handler : handlersMap.values()) {
            System.out.println(handler.getClass().toString());
            System.out.println(handler.listenTo().toString());
        }
        return (R) handlersMap.get(command.getClass()).handle(command);

    }
}
