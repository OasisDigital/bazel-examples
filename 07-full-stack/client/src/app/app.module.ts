import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PairHistoryChartComponent } from './pair-history-chart.component';
import { PairHistoryViewComponent } from './pair-history-view.component';
import { PairHistoryComponent } from './pair-history.component';
import { PairListViewComponent } from './pair-list-view.component';
import { PairListComponent } from './pair-list.component';

@NgModule({
  declarations: [
    AppComponent,
    PairHistoryComponent,
    PairHistoryViewComponent,
    PairListComponent,
    PairListViewComponent,
    PairHistoryChartComponent,
  ],
  imports: [BrowserModule, FormsModule, HttpClientModule],
  bootstrap: [AppComponent],
})
export class AppModule {}
