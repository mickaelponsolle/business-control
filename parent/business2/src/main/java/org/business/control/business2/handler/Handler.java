package org.business.control.business2.handler;

public abstract class Handler<R, C> {
    public abstract R handle(C command);

    public abstract Class listenTo();
}
