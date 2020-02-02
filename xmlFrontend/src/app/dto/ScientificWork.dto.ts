import { HeaderDto } from './Header.dto';
import { AuthorDto } from './Author.dto';
import { ReferenceDto } from './Reference.dto';

export interface ScientificWorkDto {
   headertDTO : HeaderDto;
   title: string;
   authorsDTO: Array<AuthorDto>;
   paragraphs : Array<string>;
   referenceDTO : ReferenceDto;
   comments : Array<String>;
  }