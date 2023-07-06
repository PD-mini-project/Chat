
function renderChatRooms(chatRooms) {
    var chatRoomList = document.getElementById('chat-room-list');
    chatRoomList.innerHTML = ''; // Clear the existing list

    chatRooms.forEach(function (chatRoom) {
        var chatRoomItem = document.createElement('li');
        chatRoomItem.textContent = chatRoom.title;
        chatRoomList.appendChild(chatRoomItem);
    });
}

// Fetch chat rooms and render them
fetch('/api/chatroom')
    .then(function (response) {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Error: ' + response.status);
        }
    })
    .then(function (data) {
        // Render chat rooms
        renderChatRooms(data);
    })
    .catch(function (error) {
        console.error('Error:', error);
    });

// Submit event handler for the create chat room form
document.getElementById('create-chat-room-form').addEventListener('submit', function (event) {
    event.preventDefault();

    var titleInput = document.getElementById('title-input');
    var descriptionInput = document.getElementById('description-input');

    var title = titleInput.value;
    var description = descriptionInput.value;

    // Call the createChatRoom function
    createChatRoom(title, description);

    // Reset the input fields
    titleInput.value = '';
    descriptionInput.value = '';
});

// Click event handler for the delete chat room button
document.getElementById('delete-chat-room-button').addEventListener('click', function () {
    var chatRoomId = // Get the selected chat room ID

        // Call the deleteChatRoom function
        deleteChatRoom(chatRoomId);
});

// Function to create a chat room
function createChatRoom(title, description) {
    fetch('/api/chatroom', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            title: title,
            description: description
        })
    })
        .then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error: ' + response.status);
            }
        })
        .then(function (data) {
            console.log(data);
            // Handle the created chat room data
        })
        .catch(function (error) {
            console.error('Error:', error);
        });
}

// Function to delete a chat room
function deleteChatRoom(chatRoomId) {
    fetch('/api/chatroom/' + chatRoomId, {
        method: 'DELETE'
    })
        .then(function (response) {
            if (response.ok) {
                console.log('Chat room deleted');
            } else {
                throw new Error('Error: ' + response.status);
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
        });
}