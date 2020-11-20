import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlbumModel } from '../album.model';
import { AlbumService } from '../services/album.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  album: AlbumModel;

  constructor(private router: Router, private albumService : AlbumService) { 
    this.album = new AlbumModel();
  }

  ngOnInit() {
  }
  
  saveAlbum() {
    this.albumService.addAlbum(this.album);
    this.router.navigate(['list']);
  }
}
