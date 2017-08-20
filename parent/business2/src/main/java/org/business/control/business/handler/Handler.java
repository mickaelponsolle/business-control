package org.business.control.business.handler;

public abstract class Handler<R, C> {
    public abstract R handle(C command);

    public abstract Class listenTo();
}
