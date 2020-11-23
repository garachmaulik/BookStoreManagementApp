import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlbumModel } from '../album.model';
import { AlbumService } from '../services/album.service';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  album: AlbumModel;

  constructor(private service: AlbumService,
    private router: Router,
    private route: ActivatedRoute) {
    this.album = new AlbumModel();
  }

  ngOnInit() {
    this.route.queryParams.pipe(
      filter(params => params.index)) //find the index
      .subscribe(params => {
        var paramIndex = params.index; //save the index
        this.album = this.service.getByIndex(paramIndex)
      }); //fetch data from that index
  }

  updateAlbum() {
    this.router.navigate(['list']);
  }
}
