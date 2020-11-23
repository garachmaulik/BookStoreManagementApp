import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AlbumModel } from '../album.model';

@Injectable({
  providedIn: 'root'
})
export class AlbumService {
  albumList : AlbumModel[] = [];

  constructor(private router : Router) { }

  addAlbum(album : AlbumModel){
    this.albumList.push(album);
  }

  listAlbum() : AlbumModel[]{
    return this.albumList;
  }

  deleteAlbum(index : number) : AlbumModel[] {
    return this.albumList.splice(index, 1);
  }
  update(index : number) {
    this.router.navigate(['edit'], {queryParams: {index: index}});
  }

  sortByTitle() {
    this.albumList.sort((a,b) => a.title > b.title ? 1 : (
      (a.title < b.title ? -1 : 0)
    ));
    return this.albumList;
  }

  sortByPrice() {
    this.albumList.sort((a,b) => a.price > b.price ? 1 : (
      (a.price < b.price ? -1 : 0)
    ));
    return this.albumList;
  }

  searchByArtist(artist : string) {
    return this.albumList.find(x => x.artist == artist);
  }

  searchById(id : number) {
    return this.albumList.find(x => x.id == id);
  }

  getByIndex(index: number) {
    return this.albumList[index];
  }
}
