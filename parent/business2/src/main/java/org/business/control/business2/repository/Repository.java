package org.business.control.business2.repository;

import java.util.List;

public interface Repository<TId, TRoot> {
    TRoot get(TId id);

    void add(TRoot root);

    void delete(TRoot root);

    List<TRoot> getAll();
}
