package org.business.control.business.configuration.prestation;

import org.business.control.business.aggregate.configuration.business.ConfigurationPrestationAggregate;
import org.business.control.business.command.configuration.prestation.AjouterPrestationCommand;
import org.business.control.business.event.BusinessEvent;
import org.business.control.business.repository.EventRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AjouterPrestationTest {

    @Mock
    private EventRepository eventRepository;

    @Test
    public void whenAjouterPrestationCommandThenAjouterPrestationEventPersisted() {
	AjouterPrestationCommand command = new AjouterPrestationCommand("coupe");
	Assert.assertNotNull(command.getLibelle());
	Assert.assertFalse(command.getLibelle().isEmpty());

	ConfigurationPrestationAggregate configurationPrestationAggregate = new ConfigurationPrestationAggregate(
		eventRepository);
	configurationPrestationAggregate.apply(command);

	BusinessEvent businessEvent = new BusinessEvent(command);
	Mockito.verify(eventRepository).store(Mockito.refEq(businessEvent, "dateTime"));

    }

}
