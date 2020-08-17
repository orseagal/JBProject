import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
    selector: 'dialog-overview-dialog',
    templateUrl: 'mat-dialog.html',
  })
  export class DialogOverviewDialog {
  
    constructor(
      public dialogRef: MatDialogRef<DialogOverviewDialog>,
      @Inject(MAT_DIALOG_DATA) public data: string) {}
  
    onNoClick(): void {
      this.dialogRef.close();
    }
  
  }