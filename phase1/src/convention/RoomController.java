package convention;

import convention.calendar.TimeRange;
import convention.conference.ConferenceManager;
import convention.permission.PermissionManager;
import convention.room.RoomManager;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

public class RoomController {

    Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ConferenceManager conferenceManager;
    private PermissionManager permissionManager;

    public RoomController(ConferenceManager conferenceManager, PermissionManager permissionManager) {
        this.conferenceManager = conferenceManager;
        this.permissionManager = permissionManager;
    }

    /**
     * Gets a set of UUIDs of rooms associated with this convention.
     * <p>
     * Required Permission: ATTENDEE
     *
     * @param conferenceUUID UUID of the convention to operate on
     * @param executorUUID   UUID of the user executing the command
     * @return set of UUIDs of rooms associated with the convention
     */
    public Set<UUID> getRooms(UUID conferenceUUID, UUID executorUUID) {
        permissionManager.testIsAttendee(conferenceUUID, executorUUID);
        return conferenceManager.getRoomManager(conferenceUUID).getRooms();
    }

    /**
     * Create a new room for this convention.
     * <p>
     * Required Permission: ORGANIZER
     *
     * @param conferenceUUID UUID of the convention to operate on
     * @param executorUUID   UUID of the user executing the command
     * @param roomLocation   location of the room
     * @param roomCapacity   capacity of the room
     * @return UUID of the new room
     */
    public UUID createRoom(UUID conferenceUUID, UUID executorUUID, String roomLocation, int roomCapacity) {
        permissionManager.testIsOrganizer(conferenceUUID, executorUUID);

        RoomManager roomManager = conferenceManager.getRoomManager(conferenceUUID);

        return roomManager.createRoom(roomLocation, roomCapacity);
    }

    /**
     * Sets a new room location.
     * <p>
     * Required Permission: ORGANIZER
     *
     * @param conferenceUUID UUID of the convention to operate on
     * @param executorUUID   UUID of the user executing the command
     * @param roomUUID       UUID of the room to operate on
     * @param roomLocation   new room location
     */
    public void setRoomLocation(UUID conferenceUUID, UUID executorUUID, UUID roomUUID, String roomLocation) {
        permissionManager.testIsOrganizer(conferenceUUID, executorUUID);

        RoomManager roomManager = conferenceManager.getRoomManager(conferenceUUID);

        roomManager.setRoomLocation(roomUUID, roomLocation);
    }

    /**
     * Sets a new room capacity.
     * <p>
     * Required Permission: ORGANIZER
     *
     * @param conferenceUUID UUID of the convention to operate on
     * @param executorUUID   UUID of the user executing the command
     * @param roomUUID       UUID of the room to operate on
     * @param roomCapacity   new room capacity
     */
    public void setRoomCapacity(UUID conferenceUUID, UUID executorUUID, UUID roomUUID, int roomCapacity) {
        permissionManager.testIsOrganizer(conferenceUUID, executorUUID);

        RoomManager roomManager = conferenceManager.getRoomManager(conferenceUUID);

        roomManager.setRoomCapacity(roomUUID, roomCapacity);
    }

    /**
     * Delete a room. This operation will be rejected if the room is currently used by one or more events.
     * <p>
     * Required Permission: ORGANIZER
     *
     * @param conferenceUUID UUID of the convention to operate on
     * @param executorUUID   UUID of the user executing the command
     * @param roomUUID       UUID of the room to operate on
     */
    public void deleteRoom(UUID conferenceUUID, UUID executorUUID, UUID roomUUID) {
        permissionManager.testIsOrganizer(conferenceUUID, executorUUID);
        /**
         * TODO: Do not allow this room to be deleted if it is being used by some events
         *       Having an event without a room will cause all sorts of headaches
         */

        RoomManager roomManager = conferenceManager.getRoomManager(conferenceUUID);

        roomManager.deleteRoom(roomUUID);
    }

    /**
     * Gets a room's location.
     * <p>
     * Required Permission: ATTENDEE
     *
     * @param conferenceUUID UUID of the convention to operate on
     * @param executorUUID   UUID of the user executing the command
     * @param roomUUID       UUID of the room to operate on
     * @return room location
     */
    public String getRoomLocation(UUID conferenceUUID, UUID executorUUID, UUID roomUUID) {
        permissionManager.testIsAttendee(conferenceUUID, executorUUID);

        RoomManager roomManager = conferenceManager.getRoomManager(conferenceUUID);

        return roomManager.getRoomLocation(roomUUID);
    }

    /**
     * Gets a room's capacity.
     * <p>
     * Required Permission: ATTENDEE
     *
     * @param conferenceUUID UUID of the convention to operate on
     * @param executorUUID   UUID of the user executing the command
     * @param roomUUID       UUID of the room to operate on
     * @return room capacity
     */
    public int getRoomCapacity(UUID conferenceUUID, UUID executorUUID, UUID roomUUID) {
        permissionManager.testIsAttendee(conferenceUUID, executorUUID);

        RoomManager roomManager = conferenceManager.getRoomManager(conferenceUUID);

        return roomManager.getRoomCapacity(roomUUID);
    }

    /**
     * Get a map of event UUIDs and their respective time ranges for a specific room.
     * <p>
     * Required Permission: ATTENDEE
     *
     * @param conferenceUUID UUID of the convention to operate on
     * @param executorUUID   UUID of the user executing the command
     * @param roomUUID       UUID of the room to operate on
     * @return map of event UUIDs to their time range
     */
    public Map<UUID, TimeRange> getRoomSchedule(UUID conferenceUUID, UUID executorUUID, UUID roomUUID) {
        permissionManager.testIsAttendee(conferenceUUID, executorUUID);

        RoomManager roomManager = conferenceManager.getRoomManager(conferenceUUID);

        return roomManager.getCalendarManager(roomUUID).getUUIDtoTimeRanges();
    }

}