let selected;
document.querySelectorAll('.size').forEach(element => {
  element.addEventListener('click', function() {
    document.querySelectorAll('.size').forEach(el => el.classList.remove('active'));
    this.classList.add('active');
    selected=this.textContent;
    console.log(selected);
  });
});