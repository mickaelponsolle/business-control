package org.business.control.business.aggregate.configuration.business;

import org.business.control.business.aggregate.AbstractAggregate;
import org.business.control.business.command.configuration.prestation.AjouterPrestationCommand;
import org.business.control.business.event.BusinessEvent;
import org.business.control.business.repository.EventRepository;

public class ConfigurationPrestationAggregate extends AbstractAggregate {

    public ConfigurationPrestationAggregate(EventRepository eventRepository) {
	super(eventRepository);
    }

    public void apply(AjouterPrestationCommand command) {
	eventRepository.store(new BusinessEvent(command));
    }

}
