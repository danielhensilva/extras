import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  onToggle(element: HTMLDivElement) {
    if (element.classList.contains('show')) {
      element.classList.remove('show');
    } else {
      element.classList.add('show');
    }
  }
}
