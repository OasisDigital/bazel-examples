import { Component } from '@angular/core';
import { format } from 'date-fns';

@Component({
  selector: 'hello-world',
  templateUrl: 'hello-world.component.html',
  styleUrls: [
    './hello-world.component.scss',
    './secondary-styles.scss',
  ],
})
export class HelloWorldComponent {
  name: string = 'Adolph Blaine Wolfeschleg Senior ';
  date: string = format(new Date(), 'MMMM D, YYYY');
}
