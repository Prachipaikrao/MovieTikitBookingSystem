// Sample data for seat availability
const seatAvailability = {
  'A1': true, 'A2': true, 'A3': true, 'A4': true, 'A5': true,
  'B1': true, 'B2': true, 'B3': true, 'B4': true, 'B5': true,
  'C1': true, 'C2': true, 'C3': true, 'C4': true, 'C5': true,
  'D1': true, 'D2': true, 'D3': true, 'D4': true, 'D5': true,
};

let selectedSeats = [];

// Function to display seats
function displaySeats() {
  const seatMap = document.getElementById('seat-map');
  seatMap.innerHTML = ''; // Clear previous seats

  Object.keys(seatAvailability).forEach(seat => {
    const seatDiv = document.createElement('div');
    seatDiv.classList.add('seat');
    seatDiv.id = seat;
    seatDiv.textContent = seat;
    seatDiv.addEventListener('click', () => toggleSeat(seat));
    seatMap.appendChild(seatDiv);
  });
}

// Function to toggle seat selection
function toggleSeat(seatId) {
  const seat = document.getElementById(seatId);
  if (seat.classList.contains('locked')) {
    alert('This seat is temporarily reserved. Please try again later.');
    return;
  }

  seat.classList.toggle('selected');
  updateSelectedSeats();
}

// Function to update selected seats array
function updateSelectedSeats() {
  selectedSeats = Array.from(document.querySelectorAll('.seat.selected')).map(seat => seat.id);
}

// Function to reserve seats (simulate locking seats)
function reserveSeats() {
  selectedSeats.forEach(seatId => {
    const seat = document.getElementById(seatId);
    seat.classList.remove('selected');
    seat.classList.add('locked');
    seatAvailability[seatId] = false; // Mark seat as unavailable
  });

  // Clear selected seats array
  selectedSeats = [];

  // Simulate releasing locked seats after 10 seconds (adjust as needed)
  setTimeout(() => {
    releaseLockedSeats();
  }, 10000); // 10 seconds
}

// Function to release locked seats
function releaseLockedSeats() {
  Object.keys(seatAvailability).forEach(seat => {
    if (!seatAvailability[seat]) {
      const seatElement = document.getElementById(seat);
      seatElement.classList.remove('locked');
      seatAvailability[seat] = true; // Mark seat as available again
    }
  });
}

// Event listener for reserve button
const reserveBtn = document.getElementById('reserve-btn');
reserveBtn.addEventListener('click', () => {
  if (selectedSeats.length === 0) {
    alert('Please select at least one seat.');
    return;
  }

  // Proceed to reserve seats
  reserveSeats();
});

// Initialize seat map
displaySeats();
