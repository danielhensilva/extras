import { Component, OnInit } from '@angular/core'
import { FormControl, FormGroup, Validators } from '@angular/forms'
import { Router } from '@angular/router'
import { AuthService } from './auth.service'

@Component({
  templateUrl: `app/user/profile.component.html`,
  styleUrls: ['app/user/profile.component.css']

})
export class ProfileComponent implements OnInit {

  public profileForm: FormGroup;

  private firstName: FormControl;
  private lastName: FormControl;

  private authService: AuthService;
  private router: Router;

  constructor(authService: AuthService, router: Router) {
    this.authService = authService;
    this.router = router;
  }

  ngOnInit() {
    const validators = [
      Validators.required,
      Validators.pattern('[a-zA-Z].*')
    ];

    this.firstName = new FormControl(this.authService.currentUser.firstName, validators);
    this.lastName = new FormControl(this.authService.currentUser.lastName, validators);

    this.profileForm = new FormGroup({
      firstName: this.firstName,
      lastName: this.lastName
    });
  }

  saveProfile(formValues) {
    if (!this.profileForm.valid) {
      return;
    }

    this.authService.updateCurrentUser(formValues.firstName, formValues.lastName);
    this.router.navigate(['events']);
  }

  validateField(field: FormControl, type: string) {
    if (field.untouched) {
      return true;
    }
    if (field.valid) {
      return true;
    }
    if (!type) {
      return true;
    }
    if (!field.errors) {
      return true;
    }
    if (!field.errors[type]) {
      return true;
    }
    return false;
  }

  validateFirstName(type: string) {
    return this.validateField(this.firstName, type);
  }

  validateLastName(type: string) {
    return this.validateField(this.lastName, type);
  }

  cancel() {
    this.router.navigate(['events']);
  }

}
