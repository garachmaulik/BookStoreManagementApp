import { Component, OnInit } from '@angular/core';
import { AlbumModel } from '../album.model';
import { AlbumService } from '../services/album.service';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  artist: string;
  album: AlbumModel;
  submitted: boolean;

  constructor(private service: AlbumService) {
    this.album = new AlbumModel();
  }

  ngOnInit() {
  }

  searchByArtist() {
    this.album = this.service.searchByArtist(this.artist);
    if (this.album != null)
      this.submitted = true;
  }
}
