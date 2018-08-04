import {Directive, ElementRef, Input} from '@angular/core';

@Directive({
  selector: '[appToggleShow]'
})
export class ToggleShowDirective {

  constructor(
    private el: ElementRef) { }

  @Input() set componentDirective(element: HTMLDivElement) {
    if (!element) {
      throw new Error('Element cannot be null');
    }
  }

}
