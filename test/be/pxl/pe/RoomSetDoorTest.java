package be.pxl.pe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RoomSetDoorTest {

	private Room kitchen = new Room("Kitchen");
	private Room garage = new Room("Garage");
	private Room livingRoom = new Room("Living Room");


	@Test
	public void doorPassageIsSetInBothDirections() {
		kitchen.setDoor(Direction.NORTH, garage);
		assertEquals(garage, kitchen.explore(Direction.NORTH));
		assertEquals(kitchen, garage.explore(Direction.SOUTH));
	}

	@Test
	public void doorPassageNotSetIfItsAlreadyTaken() {
		kitchen.setDoor(Direction.NORTH, garage);
		kitchen.setDoor(Direction.NORTH, livingRoom);
		assertEquals(garage, kitchen.explore(Direction.NORTH));
		assertEquals(kitchen, garage.explore(Direction.SOUTH));
	}

	@Test
	public void doorPassageNotSetIfOpositeDirectionOfOpositeRoomIsAlreadyTaken() {
		kitchen.setDoor(Direction.NORTH, garage);
		// garage's south door leads to kitchen
		// so it's south door cannot lead to living room, therefore the following setDoor is invalid
		livingRoom.setDoor(Direction.NORTH, garage);
		assertEquals(garage, kitchen.explore(Direction.NORTH));
		assertEquals(kitchen, garage.explore(Direction.SOUTH));
		assertNull(livingRoom.explore(Direction.NORTH));
	}

}
