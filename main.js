// VEDIKA Interactive Web Engine
let currentScreenId = 'screen-1';
let currentTab = 'Home';
let selectedDay = 18;
let selectedTime = '8:00 AM';
let selectedDarshan = 'General';
let selectedFilter = 'All';

export function initApp() {
  bindScreenSwitcher();
  bindBottomNav();
  bindInteractiveElements();
}

function bindScreenSwitcher() {
  document.querySelectorAll('.screen-tab-btn').forEach(btn => {
    btn.addEventListener('click', (e) => {
      const targetScreen = btn.dataset.screen;
      switchScreen(targetScreen);
    });
  });

  const btnMobile = document.getElementById('view-mobile-btn');
  const btnGallery = document.getElementById('view-gallery-btn');
  const phoneWrapper = document.getElementById('phone-simulator-wrapper');
  const galleryWrapper = document.getElementById('gallery-view-wrapper');

  if (btnMobile && btnGallery) {
    btnMobile.addEventListener('click', () => {
      btnMobile.classList.add('active');
      btnGallery.classList.remove('active');
      phoneWrapper.style.display = 'flex';
      galleryWrapper.classList.remove('active');
    });

    btnGallery.addEventListener('click', () => {
      btnGallery.classList.add('active');
      btnMobile.classList.remove('active');
      phoneWrapper.style.display = 'none';
      galleryWrapper.classList.add('active');
    });
  }
}

export function switchScreen(screenId) {
  currentScreenId = screenId;
  document.querySelectorAll('.vedika-screen').forEach(scr => scr.classList.remove('active'));
  const target = document.getElementById(screenId);
  if (target) {
    target.classList.add('active');
  }

  // Update switcher tabs
  document.querySelectorAll('.screen-tab-btn').forEach(btn => {
    btn.classList.toggle('active', btn.dataset.screen === screenId);
  });

  // Update bottom nav active state according to screen
  const navBar = document.getElementById('main-bottom-nav');
  if (screenId === 'screen-1') {
    navBar.style.display = 'none';
  } else {
    navBar.style.display = 'flex';
    updateBottomNavForScreen(screenId);
  }

  // Scroll to top
  const scrollArea = document.getElementById('main-scroll-area');
  if (scrollArea) scrollArea.scrollTop = 0;
}

function updateBottomNavForScreen(screenId) {
  let activeTabName = 'Home';
  if (screenId === 'screen-2') activeTabName = 'Home';
  else if (screenId === 'screen-3') activeTabName = 'Explore';
  else if (screenId === 'screen-6') activeTabName = 'Panchang';
  else if (screenId === 'screen-8') activeTabName = 'Profile';
  else if (screenId === 'screen-7') activeTabName = 'Home';

  document.querySelectorAll('#main-bottom-nav .bottom-nav-item').forEach(item => {
    item.classList.toggle('active', item.dataset.tab === activeTabName);
  });
}

function bindBottomNav() {
  document.querySelectorAll('#main-bottom-nav .bottom-nav-item').forEach(item => {
    item.addEventListener('click', () => {
      const tab = item.dataset.tab;
      currentTab = tab;
      if (tab === 'Home') switchScreen('screen-2');
      else if (tab === 'Explore') switchScreen('screen-3');
      else if (tab === 'Panchang') switchScreen('screen-6');
      else if (tab === 'Profile') switchScreen('screen-8');
      else if (tab === 'Bookings') switchScreen('screen-5');
    });
  });
}

function bindInteractiveElements() {
  // Screen 1 buttons
  const btnGetStarted = document.getElementById('btn-get-started');
  if (btnGetStarted) btnGetStarted.addEventListener('click', () => switchScreen('screen-2'));

  const btnGuest = document.getElementById('btn-explore-guest');
  if (btnGuest) btnGuest.addEventListener('click', () => switchScreen('screen-2'));

  // Screen 2 shortcuts
  const promoBookBtn = document.getElementById('btn-promo-book');
  if (promoBookBtn) promoBookBtn.addEventListener('click', () => switchScreen('screen-5'));

  const actPuja = document.getElementById('act-puja-booking');
  if (actPuja) actPuja.addEventListener('click', () => switchScreen('screen-3'));

  const actPandit = document.getElementById('act-pandit-ji');
  if (actPandit) actPandit.addEventListener('click', () => switchScreen('screen-7'));

  const actTemple = document.getElementById('act-temple-darshan');
  if (actTemple) actTemple.addEventListener('click', () => switchScreen('screen-4'));

  const actPanchang = document.getElementById('act-panchang');
  if (actPanchang) actPanchang.addEventListener('click', () => switchScreen('screen-6'));

  const homeAvatar = document.getElementById('home-profile-avatar');
  if (homeAvatar) homeAvatar.addEventListener('click', () => switchScreen('screen-8'));

  const popPuja = document.getElementById('popular-puja-item');
  if (popPuja) popPuja.addEventListener('click', () => switchScreen('screen-5'));

  // Back buttons
  document.querySelectorAll('.js-back-home').forEach(btn => {
    btn.addEventListener('click', () => switchScreen('screen-2'));
  });

  // Calendar Day Clicks
  document.querySelectorAll('.cal-day-cell:not(.empty)').forEach(cell => {
    cell.addEventListener('click', () => {
      document.querySelectorAll('.cal-day-cell').forEach(c => c.classList.remove('selected'));
      cell.classList.add('selected');
      selectedDay = cell.innerText;
    });
  });

  // Time Slot Clicks
  document.querySelectorAll('.time-slot-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      document.querySelectorAll('.time-slot-btn').forEach(b => b.classList.remove('selected'));
      btn.classList.add('selected');
      selectedTime = btn.innerText;
    });
  });

  // Darshan Radio Clicks
  document.querySelectorAll('.darshan-radio-card').forEach(card => {
    card.addEventListener('click', () => {
      document.querySelectorAll('.darshan-radio-card').forEach(c => c.classList.remove('active'));
      card.classList.add('active');
      selectedDarshan = card.dataset.type;
    });
  });

  // Filter Pills Clicks
  document.querySelectorAll('.filter-pill').forEach(pill => {
    pill.addEventListener('click', () => {
      pill.parentElement.querySelectorAll('.filter-pill').forEach(p => p.classList.remove('active'));
      pill.classList.add('active');
    });
  });

  // Favorite Hearts Toggle
  document.querySelectorAll('.heart-fav-btn').forEach(btn => {
    btn.addEventListener('click', (e) => {
      e.stopPropagation();
      btn.classList.toggle('active');
      btn.innerText = btn.classList.contains('active') ? '❤️' : '🤍';
    });
  });

  // Next Button in Booking Flow
  const btnNextBooking = document.getElementById('btn-next-booking');
  if (btnNextBooking) {
    btnNextBooking.addEventListener('click', () => {
      alert(`Booking Confirmed!\nDate: ${selectedDay} Oct 2026\nTime: ${selectedTime}\nRitual: Satyanarayan Puja\nAmount: ₹1,501`);
      switchScreen('screen-2');
    });
  }

  // Book Darshan Button
  const btnBookDarshan = document.getElementById('btn-book-darshan');
  if (btnBookDarshan) {
    btnBookDarshan.addEventListener('click', () => {
      alert(`Baidyanath Dham Darshan Booked!\nType: ${selectedDarshan} Darshan\nPass generated successfully.`);
      switchScreen('screen-2');
    });
  }
}

document.addEventListener('DOMContentLoaded', initApp);
